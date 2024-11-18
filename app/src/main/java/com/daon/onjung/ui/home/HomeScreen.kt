package com.daon.onjung.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.Routes
import com.daon.onjung.ui.component.button.FilledWidthButton

@Composable
internal fun HomeScreen(
    appState: OnjungAppState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        FilledWidthButton(
            text = "가게 상세 이동",
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            appState.navigate(Routes.Home.SHOP_DETAIL)
        }
    }
}