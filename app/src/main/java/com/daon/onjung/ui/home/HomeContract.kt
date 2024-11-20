package com.daon.onjung.ui.home

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.response.OnjungSummaryResponse
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class HomeContract {

    data class State(
        val isLoading: Boolean = false,
        val onjungSummary: OnjungSummaryResponse = OnjungSummaryResponse("", 0, 0)
    ) : UiState

    sealed class Event : UiEvent

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }

}