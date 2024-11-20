package com.daon.onjung.ui.donation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.daon.onjung.ui.theme.OnjungTheme

@Preview
@Composable
fun KakaoShare (
    text: String = "나의 온기 공유하기"
) {
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            style = OnjungTheme.typography.body1.copy(
                color = OnjungTheme.colors.text_1,
                fontWeight = FontWeight.Medium
            )
        )
    }

}