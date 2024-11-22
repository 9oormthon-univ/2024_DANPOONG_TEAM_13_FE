package com.daon.onjung.ui.setting

import androidx.navigation.NavOptions
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class SettingContract {
    data class State(
        val isLoading: Boolean = false,
        val isLogoutDialogVisible: Boolean = false,
        val isDeleteAccountDialogVisible: Boolean = false,
        val userName: String = "",
        val profileImgUrl: String = "",
        val notificationAllowed: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data object ToggleNotification : Event()
        data object LogoutDialogDismissed : Event()
        data object LogoutDialogOpen : Event()
        data object DeleteAccountDialogDismissed : Event()
        data object DeleteAccountDialogOpen : Event()
        data object Logout : Event()
        data object DeleteAccount : Event()
    }
    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }
}