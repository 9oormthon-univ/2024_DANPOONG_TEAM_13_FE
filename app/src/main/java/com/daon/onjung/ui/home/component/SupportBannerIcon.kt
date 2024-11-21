package com.daon.onjung.ui.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@Composable
fun SupportBannerIcon(
    modifier: Modifier = Modifier,
    imageUrl: String,
    backgroundColor: Color = Color.White,
) {
    val context = LocalContext.current

    Surface(
        shadowElevation = 10.dp,
        color = backgroundColor,
        shape = RoundedCornerShape(7.5.dp),
        modifier = modifier.size(width = 101.dp, height =  47.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .build(),
                contentDescription = "IC_COMPANY",
            )
        }
    }
}