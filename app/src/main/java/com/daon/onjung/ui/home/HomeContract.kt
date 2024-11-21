package com.daon.onjung.ui.home

import androidx.navigation.NavOptions
import com.daon.onjung.network.model.response.OnjungSummaryResponse
import com.daon.onjung.network.model.response.StoreOverviewInfo
import com.daon.onjung.util.UiEffect
import com.daon.onjung.util.UiEvent
import com.daon.onjung.util.UiState

class HomeContract {

    data class State(
        val isLoading: Boolean = false,
        val isStoreListFetching: Boolean = false,
        val isStoreListLastPage: Boolean = false,
        val storeListCurrentPage: Int = 1,
        val storeListPageSize: Int = 2,
        val storeList: List<StoreOverviewInfo> = emptyList(),
        val onjungSummary: OnjungSummaryResponse = OnjungSummaryResponse("", 0, 0)
    ) : UiState

    sealed class Event : UiEvent {
        data object LoadMoreStoreList : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()
        data class ShowSnackBar(val message: String) : Effect()
    }

}