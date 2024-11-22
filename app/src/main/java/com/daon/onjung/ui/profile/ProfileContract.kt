package com.daon.onjung.ui.profile

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.response.OnjungBriefResponse
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class ProfileContract {

    data class State(
        val isLoading: Boolean = false,
        val onjungBrief: OnjungBriefResponse = OnjungBriefResponse(0, 0),
        val onjungCount: Int = 0,
        val ticketCount: Int = 0,
        val storeList: List<String> = emptyList(),
        val remainCount: Int = 0
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