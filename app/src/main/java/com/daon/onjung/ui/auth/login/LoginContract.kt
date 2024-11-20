package com.daon.onjung.ui.auth.login

import android.content.Context
import androidx.navigation.NavOptions
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class LoginContract {

    data class State(
        val isLoading: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data class KakaoLoginButtonClicked(val context: Context) : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }

}