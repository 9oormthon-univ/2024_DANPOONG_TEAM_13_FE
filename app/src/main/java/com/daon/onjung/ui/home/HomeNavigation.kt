package com.daon.onjung.ui.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes
import com.daon.onjung.ui.home.ocr.OcrCameraScreen
import com.daon.onjung.ui.home.ocr.OcrCameraViewModel

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
        route = Routes.Home.SHOP_DETAIL
    ) {
        ShopDetailScreen(
            appState = appState
        )
    }
}