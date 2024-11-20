package com.daon.onjung.ui.auth.splash

import androidx.navigation.NavOptions
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class SplashContract {
    data class State(
        val isLoading: Boolean = false
    ) : UiState

    sealed class Event : UiEvent

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data object CheckNotificationPermission : Effect()
    }
}