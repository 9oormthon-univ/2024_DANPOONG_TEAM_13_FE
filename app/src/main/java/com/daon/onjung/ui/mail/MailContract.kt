package com.daon.onjung.ui.mail

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.response.OnjungMailResponse
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class MailContract {

    data class State(
        val isLoading: Boolean = false,
        val onjungCount: Int = 0,
        val isMailListFetching: Boolean = false,
        val isMailListLastPage: Boolean = false,
        val mailListCurrentPage: Int = 1,
        val mailListPageSize: Int = 20,
        val mailList: List<OnjungMailResponse> = emptyList()
    ) : UiState

    sealed class Event : UiEvent {
        data object LoadMoreMailList : Event()
        data class MailClicked(val id: Int) : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }

}