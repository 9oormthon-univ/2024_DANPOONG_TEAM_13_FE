package com.daon.onjung.ui.donation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.donation.component.DonationSelectPrice
import com.daon.onjung.ui.donation.component.DonationStore
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
        DonationStore()
        Spacer(modifier = Modifier.height(8.dp))
        DonationSelectPrice()
        Spacer(modifier = Modifier.weight(1f))
        FilledWidthButton(
            "카카오페이로 결제하기",
            modifier = Modifier
                .padding(horizontal = 20.dp),
            fontSize = 18
        )
        Spacer(modifier = Modifier.height(40.dp))
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