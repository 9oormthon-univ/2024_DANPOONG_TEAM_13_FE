package com.daon.onjung.ui.donation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.donation.component.KakaopayButton
import com.daon.onjung.ui.donation.component.KakaopayTopBar
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun KakaopayResultScreen (
    appState: OnjungAppState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.gray_2),
    ) {
        Spacer(modifier = Modifier.height(7.dp))
        KakaopayTopBar(
            modifier = Modifier.padding(
                end = 4.dp
            )
        ) { println("닫기") }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    append("주문중이던 앱/웹브라우저로 이동하면\n")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("결제가 완료")
                    }
                    append(" 됩니다.")
                },
                style = OnjungTheme.typography.body1.copy(
                    color = Color.Black
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        KakaopayButton(
        "확인"
        )
        Spacer(modifier = Modifier.height(11.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_kakao_c),
                contentDescription = null,
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                "Kakao Pay Corp.",
                style = OnjungTheme.typography.caption.copy(
                    color = OnjungTheme.colors.text_3,
                    fontWeight = FontWeight.Normal
                ),
            )
        }
    }
}

@Preview
@Composable
fun KakaopayResultScreenPreview() {
    OnjungTheme {
        val appState = rememberOnjungAppState()
        KakaopayResultScreen(
            appState = appState
        )
    }
}