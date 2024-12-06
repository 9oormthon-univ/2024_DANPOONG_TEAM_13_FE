package com.daon.onjung.ui.community.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.daon.onjung.R


@Preview
@Composable
fun HeartAnimation (
    modifier: Modifier = Modifier,
    isPlaying: Boolean = false,
    onAnimationEnd: () -> Unit = {}
) {
    val heartComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.community_heart)
    )
    val heartLottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            heartLottieAnimatable.animate(
                composition = heartComposition,
                clipSpec = LottieClipSpec.Frame(0, 1200),
                initialProgress = 0f,
                iterations = 1
            )
            onAnimationEnd()
        }
    }

    LottieAnimation(
        composition = heartComposition,
        progress = heartLottieAnimatable.progress,
        modifier = modifier
            .fillMaxSize(1f),
        contentScale = ContentScale.FillWidth
    )
}