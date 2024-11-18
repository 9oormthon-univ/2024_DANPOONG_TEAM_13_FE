package com.daon.onjung.ui.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
internal fun LoginScreen(
    appState: OnjungAppState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFEEDF),
                        Color.Transparent,
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.img_login_logo),
            contentDescription = "login_logo"
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 50.dp)
        ) {
            Text(
                text = "하나 둘 온기가 모여\n따뜻한 세상을 만들어가요.",
                style = OnjungTheme.typography.h1.copy(
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 40.sp
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(50.dp))

            Spacer(modifier = Modifier.weight(1f))
            KakaoLoginButton(
                onClick = {},
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(34.dp))
            Text(
                text = buildAnnotatedString {
                    append("첫 로그인 시, ")
                    pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                    append("서비스 이용약관")
                    pop()
                    append("에 동의한 것으로 간주합니다.")
                },
                style = OnjungTheme.typography.caption.copy(
                    color = OnjungTheme.colors.text_3
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(34.dp))
        }
    }
}
