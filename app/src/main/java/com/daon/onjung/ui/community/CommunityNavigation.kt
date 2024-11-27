package com.daon.onjung.ui.community

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes
import com.daon.onjung.ui.community.detail.CommunityDetailScreen
import com.daon.onjung.ui.community.detail.CommunityDetailViewModel
import com.daon.onjung.ui.community.write.CommunityWriteScreen
import com.daon.onjung.ui.community.write.CommunityWriteViewModel

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

    composable(
        route = "${Routes.Community.DETAIL}?id={id}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { entry ->
        val viewModel: CommunityDetailViewModel = hiltViewModel()
        val idString = entry.arguments?.getString("id") ?: "0"
        val id = idString.toInt()

        Log.d("CommunityNavigation", "communityGraph: id = $id")

        CommunityDetailScreen(
            appState = appState,
            viewModel = viewModel,
            postId = id
        )
    }
}