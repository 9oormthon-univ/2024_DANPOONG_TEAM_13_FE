package com.daon.onjung.ui.donation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.donation.component.DonationResultCard
import com.daon.onjung.ui.donation.component.KakaoShare
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun DonationResultScreen(
    appState: OnjungAppState,
) {
    val price by remember { mutableStateOf(30000) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(77.dp))
        Text(
            "${price}원 후원 완료",
            style = OnjungTheme.typography.h1.copy(
                color = OnjungTheme.colors.text_1,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "따뜻한 세상을 만들어 주셔서 감사합니다.",
            style = OnjungTheme.typography.body1.copy(
                color = OnjungTheme.colors.text_3,
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = Modifier.height(37.dp))
        DonationResultCard(
            modifier = Modifier.padding(horizontal = 50.dp)
        )
        Spacer(modifier = Modifier.height(27.dp))
        KakaoShare()
        Spacer(modifier = Modifier.weight(1f))
        FilledWidthButton(
            "확인",
            modifier = Modifier
                .padding(horizontal = 20.dp),
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
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