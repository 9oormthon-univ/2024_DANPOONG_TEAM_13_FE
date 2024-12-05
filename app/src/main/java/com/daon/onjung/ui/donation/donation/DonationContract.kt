package com.daon.onjung.ui.donation.donation

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.StoreCategory
import com.daon.onjung.network.model.StoreTag
import com.daon.onjung.network.model.response.StoreDetailInfo
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState


class DonationContract {
    data class State(
        val isLoading: Boolean = false,
        val storeInfo: StoreDetailInfo = StoreDetailInfo("", listOf(StoreTag.DISABLED_GROUP), "", "", "", "", StoreCategory.KOREAN, "", 0.0, 0.0, ""),
        val amount: Int = 0,
        val isEnabled: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data class AmountChangedClicked(val price: Int) : Event()
        data object ResetAmountClicked : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }
}