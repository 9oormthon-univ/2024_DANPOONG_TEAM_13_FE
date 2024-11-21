package com.daon.onjung.ui.setting

import androidx.lifecycle.viewModelScope
import com.daon.onjung.Routes
import com.daon.onjung.data.repository.AuthRepository
import com.daon.onjung.data.repository.DataStoreRepository
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.util.BaseViewModel
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
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
        // 사용자 정보 데이터 가져오는 부분
    }

    private fun handleLogout() {
        viewModelScope.launch {
            authRepository.logout().collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        dataStoreRepository.deleteTokens()
                        postEffect(SettingContract.Effect.NavigateTo(Routes.Auth.LOGIN))
                        UserApiClient.instance.logout {}
                    }

                    is ApiResult.ApiError -> {
                        postEffect(SettingContract.Effect.ShowSnackBar("로그아웃에 실패했습니다."))
                    }

                    is ApiResult.NetworkError -> {
                        postEffect(SettingContract.Effect.ShowSnackBar("네트워크 에러가 발생했습니다."))
                    }
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