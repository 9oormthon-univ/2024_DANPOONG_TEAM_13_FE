package com.daon.onjung.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.Routes
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.setting.component.Profile
import com.daon.onjung.ui.setting.component.SettingButtonList
import com.daon.onjung.ui.setting.component.SettingTitleText
import com.daon.onjung.ui.theme.OnjungTheme

val ButtonListAlarm = listOf(
    SettingButtonData(
        text = "PUSH 알림",
        isToggle = true,
        onClick = {}
    ),
)

val ButtonListLogin = listOf(
    SettingButtonData(
        text = "로그아웃",
        icon = R.drawable.ic_arrow_right,
        onClick = {}
    ),
    SettingButtonData(
        text = "탈퇴하기",
        icon = R.drawable.ic_arrow_right,
        onClick = {}
    ),
)

@Composable
internal fun SettingScreen(
    appState: OnjungAppState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(
            "설정",
            rightIcon = null,
            leftIconOnClick = {
                appState.navigate(Routes.Home.ROUTE)
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Profile(
            name = "카카오 닉네임",
            email = "example@example.com",
        )
        Spacer(modifier = Modifier.height(12.dp))
        SettingTitleText(
            title = "알림 설정",
            color = OnjungTheme.colors.text_3,
            modifier = Modifier.padding(start = 21.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        SettingButtonList(
            ButtonListAlarm,
            modifier = Modifier.padding(horizontal = 12.5.dp)
        )
        Spacer(modifier = Modifier.height(33.dp))
        SettingTitleText(
            title = "계정 정보",
            color = OnjungTheme.colors.text_2,
            modifier = Modifier.padding(start = 21.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        SettingButtonList(
            ButtonListLogin,
            modifier = Modifier.padding(horizontal = 12.5.dp)
        )
    }
}

@Preview
@Composable
fun SettingScreenPreview() {
    OnjungTheme {
        val appState = rememberOnjungAppState()
        SettingScreen(
            appState = appState
        )
    }
}