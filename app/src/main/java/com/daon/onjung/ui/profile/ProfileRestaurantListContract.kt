package com.daon.onjung.ui.profile

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.response.SharedRestaurantResponse
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class ProfileRestaurantListContract {

    data class State(
        val isLoading: Boolean = false,
        val restaurantList: List<SharedRestaurantResponse> = emptyList()
    ) : UiState

    sealed class Event : UiEvent {
        data class RestaurantClicked(val id: Int) : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }
}