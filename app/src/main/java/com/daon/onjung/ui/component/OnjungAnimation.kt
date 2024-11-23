package com.daon.onjung.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
//            iterations = LottieConstants.IterateForever
        )
    }

    LaunchedEffect(confettiComposition) {
        confettiLottieAnimatable.animate(
            composition = confettiComposition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f,
//            iterations = LottieConstants.IterateForever
        )
    }

    LaunchedEffect(toryCelebrationComposition) {
        toryCelebrationLottieAnimatable.animate(
            composition = toryCelebrationComposition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f,
//            iterations = LottieConstants.IterateForever
        )
    }
    Box {
        LottieAnimation(
            composition = twinkleComposition,
            progress = twinkleLottieAnimatable.progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        LottieAnimation(
            composition = confettiComposition,
            progress = confettiLottieAnimatable.progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp),
            contentScale = ContentScale.Crop
        )

        LottieAnimation(
            composition = toryCelebrationComposition,
            progress = toryCelebrationLottieAnimatable.progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp),
            contentScale = ContentScale.Crop
        )
    }
}