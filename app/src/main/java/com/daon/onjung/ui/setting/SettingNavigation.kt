package com.daon.onjung.ui.setting

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes

@RequiresApi(Build.VERSION_CODES.O)
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