package com.daon.onjung.ui.donation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.daon.onjung.OnjungAppState
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.theme.OnjungTheme


@Composable
fun DonationScreen(
    appState: OnjungAppState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.gray_2),
    ) {
        TopBar(
            "동참하기",
            rightIcon = null
        )
    }
}

@Preview
@Composable
fun DonationScreenPreview(
) {
    val appState = rememberOnjungAppState()
    OnjungTheme {
        DonationScreen(
            appState = appState
        )
    }
}