package com.daon.onjung.ui.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes
import com.daon.onjung.ui.home.ocr.OcrCameraScreen
import com.daon.onjung.ui.home.ocr.OcrCameraViewModel
import com.daon.onjung.ui.home.shopdetail.ShopDetailScreen
import com.daon.onjung.ui.home.shopdetail.ShopDetailViewModel

fun NavGraphBuilder.homeGraph(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
    composable(
        route = Routes.Home.ROUTE
    ) {
        val viewModel: HomeViewModel = hiltViewModel()

        HomeScreen(
            appState = appState,
            bottomSheetState = bottomSheetState,
            viewModel = viewModel
        )
    }

    composable(
        route = Routes.Home.OCR_CAMERA
    ) {
        val viewModel: OcrCameraViewModel = hiltViewModel()

        OcrCameraScreen(
            appState = appState,
            bottomSheetState = bottomSheetState,
            viewModel = viewModel
        )
    }

    composable(
        route = "${Routes.Home.SHOP_DETAIL}?shopId={shopId}",
        arguments = listOf(
            navArgument("shopId") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { entry ->
        val shopIdString = entry.arguments?.getString("shopId") ?: "0"
        val shopId = shopIdString.toInt()
        val viewModel: ShopDetailViewModel = hiltViewModel()

        ShopDetailScreen(
            appState = appState,
            viewModel = viewModel,
            shopId = shopId
        )
    }
}