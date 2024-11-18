package com.daon.onjung.ui.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes

fun NavGraphBuilder.profileGraph(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
    composable(
        route = Routes.Profile.ROUTE
    ) {
        ProfileScreen(
            appState = appState
        )
    }

    composable(
        route = Routes.Profile.RESTAURANT_LIST
    ) {
        ProfileRestaurantListScreen(
            appState = appState
        )
    }
}