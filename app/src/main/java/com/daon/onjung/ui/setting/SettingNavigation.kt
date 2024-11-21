package com.daon.onjung.ui.setting

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes

fun NavGraphBuilder.settingGraph(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
    composable(
        route = Routes.Setting.ROUTE
    ) {
        val viewModel: SettingViewModel = hiltViewModel()
        SettingScreen(
            appState = appState,
            viewModel = viewModel
        )
    }
}