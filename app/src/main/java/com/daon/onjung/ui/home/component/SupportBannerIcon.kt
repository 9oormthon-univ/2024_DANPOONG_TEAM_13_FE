package com.daon.onjung.ui.home.component

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun SupportBannerIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = R.drawable.ic_cj_icon,
    contentDescription: String = "",
    color: Color = Color.White,
) {
    Surface(
        shadowElevation = 10.dp,
        color = color,
        shape = RoundedCornerShape(7.5.dp),
        modifier = modifier.size(width = 101.dp, height =  47.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = contentDescription,
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Preview(showBackground=true)
@Composable
fun CjPreview() {
    OnjungTheme {
        SupportBannerIcon (
            icon = R.drawable.ic_cj_icon,
            contentDescription = "img_cj",
            color = Color.White
        )
    }
}

@SuppressLint("MissingColorAlphaChannel")
@Preview(showBackground=true)
@Composable
fun KakaoPreview() {
    OnjungTheme {
        SupportBannerIcon (
            icon = R.drawable.ic_kakao_icon,
            contentDescription = "img_kakao",
            color = Color(0xFFFAE100)
        )
    }
}

@Preview(showBackground=true)
@Composable
fun GoormPreview() {
    OnjungTheme {
        SupportBannerIcon (
            icon = R.drawable.ic_goorm_icon,
            contentDescription = "img_goorm",
            color = Color.White
        )
    }
}

@Preview(showBackground=true)
@Composable
fun NongshimPreview() {
    OnjungTheme {
        SupportBannerIcon (
            icon = R.drawable.ic_nongsim_icon,
            contentDescription = "img_nongshim",
            color = Color(0xFF222222)
        )
    }
}

@Preview(showBackground=true)
@Composable
fun CoupangPreview() {
    OnjungTheme {
        SupportBannerIcon (
            icon = R.drawable.ic_coupang_icon,
            contentDescription = "img_coupang",
            color = Color.White
        )
    }
}