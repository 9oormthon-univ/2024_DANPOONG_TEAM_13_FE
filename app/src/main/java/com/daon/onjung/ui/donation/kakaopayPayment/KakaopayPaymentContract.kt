package com.daon.onjung.ui.donation.kakaopayPayment

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.StoreCategory
import com.daon.onjung.network.model.StoreTag
import com.daon.onjung.network.model.response.StoreDetailInfo
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class KakaopayPaymentContract {
    data class State(
        val isLoading: Boolean = false,
        val isChecked: Boolean = false,
        val storeInfo: StoreDetailInfo = StoreDetailInfo("", listOf(StoreTag.DISABLED_GROUP), "", "", "", "", StoreCategory.KOREAN, "", ""),
    ) : UiState

    sealed class Event : UiEvent {
        data class CheckBoxClicked(val isChecked: Boolean) : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }
}