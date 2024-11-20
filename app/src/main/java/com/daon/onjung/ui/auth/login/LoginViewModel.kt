package com.daon.onjung.ui.auth.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.daon.onjung.Routes
import com.daon.onjung.data.repository.AuthRepository
import com.daon.onjung.data.repository.DataStoreRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.LoginProvider
import com.daon.onjung.util.BaseViewModel
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<LoginContract.State, LoginContract.Event, LoginContract.Effect>(
    initialState = LoginContract.State()
) {
    override fun reduceState(event: LoginContract.Event) {
        viewModelScope.launch {
            when (event) {
                is LoginContract.Event.KakaoLoginButtonClicked -> {
                    kakaoLogin(event.context)
                }
            }
        }
    }

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            viewModelScope.launch {
                error.printStackTrace()
                postEffect(LoginContract.Effect.ShowSnackBar("카카오톡으로 로그인 실패"))
            }
        } else if (token != null) {
            socialLogin(token.accessToken)
        }
    }

    private fun kakaoLogin(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    error.printStackTrace()
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        viewModelScope.launch {
                            postEffect(LoginContract.Effect.ShowSnackBar("카카오톡으로 로그인 실패"))
                        }
                        return@loginWithKakaoTalk
                    }
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.i("LoginViewModel", "accessToken: ${token.accessToken}")
                    socialLogin(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    private fun socialLogin(token: String) = viewModelScope.launch {
        authRepository.kakaoLogin(token, LoginProvider.KAKAO).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect { result ->
            updateState(currentState.copy(isLoading = true))
            when (result) {
                is ApiResult.Success -> {
                    dataStoreRepository.setAccessToken(result.data?.data?.accessToken ?: "")
                    dataStoreRepository.setRefreshToken(result.data?.data?.refreshToken ?: "")
                    patchDeviceToken()

                    postEffect(LoginContract.Effect.NavigateTo(Routes.Home.ROUTE))
                }

                is ApiResult.ApiError -> {
                    postEffect(LoginContract.Effect.ShowSnackBar(result.message))
                }

                is ApiResult.NetworkError -> {
                    postEffect(LoginContract.Effect.ShowSnackBar("네트워크 오류가 발생했습니다."))
                }
            }
        }
    }

    private fun patchDeviceToken(){
        val firebaseMessaging = FirebaseMessaging.getInstance()

        firebaseMessaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                viewModelScope.launch {
                    authRepository.patchDeviceToken(token).collect { result ->
                        when (result) {
                            is ApiResult.Success -> {
                                Log.d("patchDeviceToken", "Success")
                            }

                            is ApiResult.ApiError -> {
                                Log.d("patchDeviceToken", "ApiError")
                            }

                            is ApiResult.NetworkError -> {
                                Log.d("patchDeviceToken", "NetworkError")
                            }
                        }
                    }
                }
            }
        }
    }
}