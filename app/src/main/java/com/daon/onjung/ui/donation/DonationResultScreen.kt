package com.daon.onjung.ui.donation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.daon.onjung.OnjungAppState
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun DonationResultScreen(
    appState: OnjungAppState,
) {

}

@Preview
@Composable
fun DonationResultScreenPreview() {
    val appState = rememberOnjungAppState()
    OnjungTheme {
        DonationResultScreen(
            appState = appState
        )
    }
}