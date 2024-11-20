package com.daon.onjung.ui.donation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R

@Preview
@Composable
fun KakaopayIcon(
) {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .background(color = Color(0xFFFFEB00))
            .padding(start = 9.38.dp, end = 10.55.dp, top = 7.92.dp, bottom = 6.94.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_kakaopay),
            contentDescription = "Kakaopay Icon",
            tint = Color.Black
        )
    }

}