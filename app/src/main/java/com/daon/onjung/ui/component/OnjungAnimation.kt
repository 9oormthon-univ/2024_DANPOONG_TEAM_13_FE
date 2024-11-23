package com.daon.onjung.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
fun OnjungAnimation (

) {
    val twinkleComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.twinkle)
    )
    val confettiComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.confetti)
    )
    val toryCelebrationComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.tory_celebration)
    )
    val twinkleLottieAnimatable = rememberLottieAnimatable()
    val confettiLottieAnimatable = rememberLottieAnimatable()
    val toryCelebrationLottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(twinkleComposition) {
        twinkleLottieAnimatable.animate(
            composition = twinkleComposition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f,
            iterations = 3
        )
    }

    LaunchedEffect(confettiComposition) {
        confettiLottieAnimatable.animate(
            composition = confettiComposition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f,
            iterations = 3
        )
    }

    LaunchedEffect(toryCelebrationComposition) {
        toryCelebrationLottieAnimatable.animate(
            composition = toryCelebrationComposition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f,
            iterations = 3
        )
    }
    Box (
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = twinkleComposition,
            progress = twinkleLottieAnimatable.progress,
            modifier = Modifier
                .fillMaxSize(1f),
            contentScale = ContentScale.Crop
        )

        LottieAnimation(
            composition = confettiComposition,
            progress = confettiLottieAnimatable.progress,
            modifier = Modifier
                .fillMaxSize(0.8f),
            contentScale = ContentScale.Crop
        )

        LottieAnimation(
            composition = toryCelebrationComposition,
            progress = toryCelebrationLottieAnimatable.progress,
            modifier = Modifier
                .fillMaxSize(1f),
            contentScale = ContentScale.Crop
        )
    }
}