package com.daon.onjung.ui.profile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        val viewModel: ProfileViewModel = hiltViewModel()

        ProfileScreen(
            appState = appState,
            viewModel = viewModel
        )
    }

    composable(
        route = Routes.Profile.RESTAURANT_LIST
    ) {
        val viewModel: ProfileRestaurantListViewModel = hiltViewModel()

        ProfileRestaurantListScreen(
            appState = appState,
            viewModel = viewModel
        )
    }

    composable(
        route = "${Routes.Profile.TICKET_LIST}?ticketCount={ticketCount}",
        arguments = listOf(
            navArgument("shopId") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { entry ->
        val viewModel: ProfileTicketListViewModel = hiltViewModel()
        val ticketCountString = entry.arguments?.getString("ticketCount") ?: "0"
        val ticketCount = ticketCountString.toInt()
        ProfileTicketListScreen(
            appState = appState,
            bottomSheetState = bottomSheetState,
            viewModel = viewModel,
            ticketCount = ticketCount
        )
    }
}