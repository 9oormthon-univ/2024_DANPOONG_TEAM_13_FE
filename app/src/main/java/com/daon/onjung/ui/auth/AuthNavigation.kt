package com.daon.onjung.ui.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daon.onjung.OnjungAppState
import com.daon.onjung.Routes
import com.daon.onjung.ui.auth.login.LoginScreen
import com.daon.onjung.ui.auth.splash.SplashScreen

fun NavGraphBuilder.authGraph(
    appState: OnjungAppState
) {
    composable(
        route = Routes.Auth.ROUTE
    ) {
        SplashScreen(
            appState = appState
        )
    }

    composable(
        route = Routes.Auth.LOGIN
    ) {
        LoginScreen(
            appState = appState
        )
    }
}