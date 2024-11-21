package com.daon.onjung.ui.home.shopdetail

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.StoreCategory
import com.daon.onjung.network.model.StoreTag
import com.daon.onjung.network.model.response.EventDetailInfo
import com.daon.onjung.network.model.response.OnjungDetailInfo
import com.daon.onjung.network.model.response.StoreDetailInfo
import com.daon.onjung.network.model.response.StoreHistory
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class ShopDetailContract {

    data class State(
        val isLoading: Boolean = false,
        val isExpanded: Boolean = false,
        val storeInfo: StoreDetailInfo = StoreDetailInfo("", listOf(StoreTag.DISABLED_GROUP), "", "", "", "", StoreCategory.KOREAN, "", ""),
        val eventInfo: EventDetailInfo = EventDetailInfo(0, 0, 0),
        val onjungInfo: OnjungDetailInfo = OnjungDetailInfo(0, 0, 0, 0),
        val storeHistories: List<StoreHistory> = emptyList(),
        val isOnjungShareDialogVisible: Boolean = false
    ) : UiState

    sealed class Event : UiEvent {
        data class ToggleExpand(val isExpanded: Boolean) : Event()
        data class OnjungShareClicked(val id: Int) : Event()
        data object OnjungShareDialogDismissed : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }

}