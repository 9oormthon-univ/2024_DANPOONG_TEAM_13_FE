package com.daon.onjung.ui.donation.kakaopayResult

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.StoreTag
import com.daon.onjung.network.model.response.DonationStoreInfo
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class KakaopayResultContract {
    data class State(
        val isLoading: Boolean = false,
        val isDonationCompleteDialogVisible: Boolean = false,
        val issueDate: String = "",
        val donationStoreInfo: DonationStoreInfo? = DonationStoreInfo("", StoreTag.DISABLED_GROUP, "")
    ) : UiState

    sealed class Event : UiEvent {
        data class DonationCompleteClicked(val shopId: Int, val amount: Int) : Event()
        data object DonationCompleteDialogDismissed : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }
}