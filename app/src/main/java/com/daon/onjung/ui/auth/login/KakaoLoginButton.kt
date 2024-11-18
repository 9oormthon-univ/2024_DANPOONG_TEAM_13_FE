package com.daon.onjung.ui.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun KakaoLoginButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 18.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFBE400)
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_kakao),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Text(
                "카카오로 3초만에 시작하기",
                modifier = Modifier.fillMaxWidth(),
                style = OnjungTheme.typography.body1.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF3E1A1D)
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun KakaoLoginButtonPreview() {
    OnjungTheme {
        KakaoLoginButton()
    }
}