package com.daon.onjung.network.interceptor

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.daon.onjung.BuildConfig
import com.daon.onjung.data.repository.DataStoreRepository
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.UserTokenResponse
import com.daon.onjung.network.service.AuthService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    @ApplicationContext private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = dataStoreRepository.getAccessToken()
        val refreshToken = dataStoreRepository.getRefreshToken()

        val originalRequest = chain.request()
        val authenticationRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        val response = chain.proceed(authenticationRequest)

        when (response.code) {
            401 -> {
                val newToken = runBlocking { updateToken(refreshToken, context) }

                if (newToken.isSuccessful) {
                    val newAccessToken = newToken.body()?.data?.accessToken ?: ""
                    val newRefreshToken = newToken.body()?.data?.refreshToken ?: ""

                    dataStoreRepository.setAccessToken(newAccessToken)
                    dataStoreRepository.setRefreshToken(newRefreshToken)

                    val newAuthenticationRequest = originalRequest.newBuilder()
                        .header("Authorization", "Bearer $newAccessToken")
                        .build()

                    return chain.proceed(newAuthenticationRequest)
                } else {
                    dataStoreRepository.deleteTokens()
                }
            }
        }

        return response
    }
}

private suspend fun updateToken(
    refreshToken: String,
    context: Context
): retrofit2.Response<BaseResponse<UserTokenResponse>> {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient(refreshToken, context))
        .build()
    val authService = retrofit.create(AuthService::class.java)

    return authService.reissueToken()
}

private fun createOkHttpClient(
    refreshToken: String,
    context: Context
): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }
        )
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $refreshToken")
                .build()

            chain.proceed(modifiedRequest)
        }

    if (BuildConfig.DEBUG) {
        builder.addNetworkInterceptor(ChuckerInterceptor(context))
    }

    return builder.build()
}
