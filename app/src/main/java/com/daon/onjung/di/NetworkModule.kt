package com.daon.onjung.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.daon.onjung.BuildConfig
import com.daon.onjung.network.adapter.ApiResultCallAdapterFactory
import com.daon.onjung.network.converter.EnumConverterFactory
import com.daon.onjung.network.interceptor.AuthInterceptor
import com.daon.onjung.network.service.AuthService
import com.daon.onjung.network.service.CompanyService
import com.daon.onjung.network.service.OnjungService
import com.daon.onjung.network.service.StoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(
        authInterceptor: AuthInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                }
            )

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(ChuckerInterceptor(context))
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(ApiResultCallAdapterFactory())
            .addConverterFactory(EnumConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesOnjungService(retrofit: Retrofit): OnjungService =
        retrofit.create(OnjungService::class.java)

    @Provides
    @Singleton
    fun providesStoreService(retrofit: Retrofit): StoreService =
        retrofit.create(StoreService::class.java)

    @Provides
    @Singleton
    fun providesAuthInterceptor(retrofit: Retrofit): CompanyService =
        retrofit.create(CompanyService::class.java)
}