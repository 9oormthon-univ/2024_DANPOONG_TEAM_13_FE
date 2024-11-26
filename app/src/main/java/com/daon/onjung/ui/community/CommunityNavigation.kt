package com.daon.onjung.ui.community

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes

fun NavGraphBuilder.communityGraph(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
    composable(
        route = Routes.Community.ROUTE
    ) {
        val viewModel: CommunityViewModel = hiltViewModel()

        CommunityScreen(
            appState = appState,
            viewModel = viewModel
        )
    }

    composable(
        route = Routes.Community.WRITE
    ) {
        val viewModel: CommunityWriteViewModel = hiltViewModel()

        CommunityWriteScreen(
            appState = appState,
            viewModel = viewModel
        )
    }
}