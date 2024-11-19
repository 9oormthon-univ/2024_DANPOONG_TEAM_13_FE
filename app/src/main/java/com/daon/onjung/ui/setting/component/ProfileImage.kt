package com.daon.onjung.ui.setting.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.fallback
import com.daon.onjung.R


@Composable
fun ProfileImage(
    imageUrl: String? = null,
    modifier: Modifier = Modifier,
    placeholder: Painter = painterResource(id = R.drawable.ic_profile_icon) // 기본 이미지 설정
) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest
            .Builder(context)
            .data(imageUrl)
            .placeholderMemoryCacheKey("profile_placeholder")
            .error(R.drawable.ic_profile_icon)
            .fallback(R.drawable.ic_profile_icon)
            .build(),
        contentDescription = "Profile Image",
        modifier = modifier
            .size(60.dp)
            .clip(CircleShape),
        placeholder = placeholder
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileImagePreview() {
    ProfileImage()
}