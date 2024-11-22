package com.daon.onjung.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavOptions
import com.daon.onjung.R
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
        val storeListPageSize: Int = 20,
        val storeList: List<StoreOverviewInfo> = emptyList(),
        val supportCompanies: List<IconData> = listOf(
            IconData(
                icon = R.drawable.ic_nongsim_icon,
                contentDescription = "ic_nongsim_icon",
                color = Color(0xFF222222)
            ),
            IconData(
                icon = R.drawable.ic_cj_icon,
                contentDescription = "ic_cj_icon",
            ),
            IconData(
                icon = R.drawable.ic_goorm_icon,
                contentDescription = "ic_goorm_icon",
            ),
            IconData(
                icon = R.drawable.ic_kakao_icon,
                contentDescription = "ic_kakao_icon",
                color = Color(0xFFFAE100)
            ),
            IconData(
                icon = R.drawable.ic_coupang_icon,
                contentDescription = "ic_coupang_icon",
            )
        ),
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

data class IconData(
    @DrawableRes val icon: Int,
    val contentDescription: String,
    val color: Color = Color.White
)