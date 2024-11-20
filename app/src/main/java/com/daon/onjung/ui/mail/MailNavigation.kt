package com.daon.onjung.ui.mail

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes

fun NavGraphBuilder.mailGraph(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
    composable(
        route = Routes.Mail.ROUTE
    ) {
        val viewModel: MailViewModel = hiltViewModel()

        MailScreen(
            appState = appState,
            viewModel = viewModel
        )
    }
}