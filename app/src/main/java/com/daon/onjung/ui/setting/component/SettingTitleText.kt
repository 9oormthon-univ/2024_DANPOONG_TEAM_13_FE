package com.daon.onjung.ui.setting.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.daon.onjung.ui.theme.OnjungTheme


@Preview(showBackground = true)
@Composable
fun SettingTitleText(
    title: String = "알림 설정",
    modifier: Modifier = Modifier,
    color: Color = OnjungTheme.colors.text_1,
) {
    Text(
        title,
        modifier = modifier.fillMaxWidth(),
        style = OnjungTheme.typography.body2.copy(
            color = color
        )
    )
}