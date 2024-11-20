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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.donation.component.KakaoPrivateCheck
import com.daon.onjung.ui.donation.component.KakaopayButton
import com.daon.onjung.ui.donation.component.KakaopayCardview
import com.daon.onjung.ui.donation.component.KakaopayPayPoint
import com.daon.onjung.ui.donation.component.KakaopayTopBar
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun KakaopayPaymentScreen(
    appState: OnjungAppState,
) {
    var isChecked by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.gray_2),
    ){
        Spacer(modifier = Modifier.height(7.dp))
        KakaopayTopBar(
            modifier = Modifier.padding(
                end = 4.dp
            )
        ) { println("닫기") }
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            "온정 - 헌신에 보답하는 감사의 식탁탁탁탁탁...",
            style = OnjungTheme.typography.body2.copy(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(start = 18.dp)
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            "10,000원",
            style = OnjungTheme.typography.h1.copy(
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.padding(start = 18.dp)
        )
        Spacer(modifier = Modifier.height(7.dp))
        KakaopayPayPoint(
            modifier = Modifier.padding(horizontal = 18.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        KakaopayCardview(
            modifier = Modifier.padding(horizontal = 38.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
        KakaoPrivateCheck(
            idChecked = isChecked,
            onCheckBtnClick = { isChecked = !isChecked },
            onPrivateClick = { /* 여기에 개인정보 보기 이벤트를 처리하는 로직을 추가 */ },
            modifier = Modifier.padding(horizontal = 25.dp)
        )
        Spacer(modifier = Modifier.height(11.13.dp))
        KakaopayButton(
            "동의하고 10,000원 결제하기",
            modifier = Modifier
                .padding(horizontal = 14.dp)
        )
    }
}

@Preview
@Composable
fun KakaopayPaymentScreenPreview() {
    OnjungTheme {
        val appState = rememberOnjungAppState()
        KakaopayPaymentScreen(
            appState = appState
        )
    }
}