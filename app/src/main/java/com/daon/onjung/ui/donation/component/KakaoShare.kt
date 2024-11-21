package com.daon.onjung.ui.donation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Preview(showBackground = true)
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
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(R.drawable.ic_kakao_logo),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }

}