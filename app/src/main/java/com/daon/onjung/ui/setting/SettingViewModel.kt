package com.daon.onjung.ui.setting

import androidx.lifecycle.viewModelScope
import androidx.navigation.navOptions
import com.daon.onjung.Constants
import com.daon.onjung.Routes
import com.daon.onjung.data.repository.AuthRepository
import com.daon.onjung.data.repository.DataStoreRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<SettingContract.State, SettingContract.Event, SettingContract.Effect>(
    initialState = SettingContract.State()
) {
    override fun reduceState(event: SettingContract.Event) {
        viewModelScope.launch {
            when (event) {
                is SettingContract.Event.ToggleNotification -> toggleNotification()
                is SettingContract.Event.LogoutDialogDismissed -> logoutDialogDismissed()
                is SettingContract.Event.LogoutDialogOpen -> logoutDialogOpen()
                is SettingContract.Event.DeleteAccountDialogDismissed -> deleteAccountDialogDismissed()
                is SettingContract.Event.DeleteAccountDialogOpen -> deleteAccountDialogOpen()
                is SettingContract.Event.Logout -> handleLogout()
                is SettingContract.Event.DeleteAccount -> handleDeleteAccount()
            }
        }
    }

    init {
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            authRepository.getUserProfile().collect {
                when (it) {
                    is ApiResult.Success -> {
                        it.data?.data?.let { result ->
                            updateState(currentState.copy(
                                userName = result.userName,
                                profileImgUrl = result.profileImgUrl,
                                notificationAllowed = result.notificationAllowed
                            ))
                        }
                    }

                    is ApiResult.ApiError -> {
                        postEffect(SettingContract.Effect.ShowSnackBar(it.message))
                    }

                    is ApiResult.NetworkError -> {
                        postEffect(SettingContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                    }
                }
            }
        }
    }

    private fun handleLogout() {
        viewModelScope.launch {
            authRepository.logout().collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        dataStoreRepository.deleteTokens()
                        postEffect(SettingContract.Effect.NavigateTo(Routes.Auth.LOGIN, navOptions{
                            popUpTo(Routes.Auth.LOGIN) {
                                inclusive = true
                            }
                        }))
                        UserApiClient.instance.logout {}
                    }

                    is ApiResult.ApiError -> {
                        postEffect(SettingContract.Effect.ShowSnackBar("로그아웃에 실패했습니다."))
                    }

                    is ApiResult.NetworkError -> {
                        postEffect(SettingContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                    }
                }
            }
        }
    }

    private fun toggleNotification() = viewModelScope.launch {
        authRepository.patchNotificationAllowed().onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect {
            updateState(currentState.copy(isLoading = false))
            when (it) {
                is ApiResult.Success -> {
                    it.data?.data?.let { result ->
                        updateState(currentState.copy(notificationAllowed = result.notificationAllowed))
                    }
                }

                is ApiResult.ApiError -> {
                    postEffect(SettingContract.Effect.ShowSnackBar("알림 설정 변경에 실패했습니다."))
                }

                is ApiResult.NetworkError -> {
                    postEffect(SettingContract.Effect.ShowSnackBar(Constants.NETWORK_ERROR_MESSAGE))
                }
            }
        }
    }

    private fun handleDeleteAccount() {
        viewModelScope.launch {
            authRepository.deleteAccount().collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        dataStoreRepository.deleteTokens()
                        postEffect(SettingContract.Effect.NavigateTo(Routes.Auth.LOGIN))
                        UserApiClient.instance.unlink {}
                    }

                    is ApiResult.ApiError -> {
                        postEffect(SettingContract.Effect.ShowSnackBar("회원 탈퇴에 실패했습니다."))
                    }

                    is ApiResult.NetworkError -> {
                        postEffect(SettingContract.Effect.ShowSnackBar("네트워크 에러가 발생했습니다."))
                    }
                }
            }
        }
    }

    private fun logoutDialogDismissed() {
        updateState(currentState.copy(isLogoutDialogVisible = false))
    }

    private fun deleteAccountDialogDismissed() {
        updateState(currentState.copy(isDeleteAccountDialogVisible = false))
    }

    private fun logoutDialogOpen() {
        updateState(currentState.copy(isLogoutDialogVisible = true))
    }

    private fun deleteAccountDialogOpen() {
        updateState(currentState.copy(isDeleteAccountDialogVisible = true))
    }
}