package com.daon.onjung.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes

fun NavGraphBuilder.homeGraph(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
    composable(
        route = Routes.Home.ROUTE
    ) {
        HomeScreen(
            appState = appState,
            bottomSheetState = bottomSheetState
        )
    }

    composable(
        route = Routes.Home.CAMERA
    ) {
        CameraScreen(
            appState = appState,
            bottomSheetState = bottomSheetState
        )
    }

    composable(
        route = Routes.Home.SHOP_DETAIL
    ) {
        ShopDetailScreen(
            appState = appState
        )
    }
}