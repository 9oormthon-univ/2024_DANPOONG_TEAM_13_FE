package com.daon.onjung.ui.donation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.donation.component.KakaopayButton
import com.daon.onjung.ui.donation.component.KakaopayIcon
import com.daon.onjung.ui.theme.OnjungTheme


@Composable
fun KakaopayScreen(
    appState: OnjungAppState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.white),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KakaopayIcon()
            Spacer(modifier = Modifier.height(19.dp))
            KakaopayText( buildAnnotatedString {
                append("카카오페이로 결제하려면\n")
                withStyle(style = SpanStyle(color = Color(0xFF008DFF))) {
                    append("다음 버튼")
                }
                append("을 눌러주세요")
            } )
            Spacer(modifier = Modifier.height(40.dp))
            KakaopayButton(
                modifier = Modifier
                    .padding(horizontal = 52.dp)
            )
        }

    }
}

@Composable
fun KakaopayText(
    text: AnnotatedString
) {
    Text(
        text = text,
        style = OnjungTheme.typography.h1.copy(
            color = Color.Black,
            lineHeight = 40.sp
        ),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun KakaopayScreenPreview() {
    OnjungTheme {
        val appState = rememberOnjungAppState()
        KakaopayScreen(
            appState = appState
        )
    }
}