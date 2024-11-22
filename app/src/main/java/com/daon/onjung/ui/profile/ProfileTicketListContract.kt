package com.daon.onjung.ui.profile

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.response.MealTicketStoreResponse
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class ProfileTicketListContract {

    data class State(
        val isLoading: Boolean = false,
        val isTicketListFetching: Boolean = false,
        val isTicketListLastPage: Boolean = false,
        val ticketListCurrentPage: Int = 1,
        val ticketListPageSize: Int = 20,
        val ticketList: List<MealTicketStoreResponse> = emptyList()
    ) : UiState

    sealed class Event : UiEvent {
        data object LoadMoreTicketList: Event()
        data class MealTicketClicked(val id: Int) : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
        data class ShowTicketBottomSheet(
            val name: String,
            val address: String,
            val qrCodeImgUrl: String,
            val expirationDate: String
        ) : Effect()
    }
}