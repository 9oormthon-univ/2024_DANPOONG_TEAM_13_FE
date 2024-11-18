package com.daon.onjung.ui.auth.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.navOptions
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.Routes
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.delay

@Composable
internal fun SplashScreen(
    appState: OnjungAppState
) {
    LaunchedEffect(Unit) {
        delay(1500)
        appState.navigate(
            Routes.Auth.LOGIN,
            navOptions {
                 popUpTo(Routes.Auth.ROUTE) { inclusive = true }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.white),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_app_logo),
                contentDescription = "App Logo",
                tint = OnjungTheme.colors.main_coral
            )
            Spacer(modifier = Modifier.height(44.dp))
            Text(
                text = "온정과 시작하는\n선행의 선순환",
                style = OnjungTheme.typography.h2.copy(
                    color = OnjungTheme.colors.main_coral,
                    lineHeight = 30.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}
