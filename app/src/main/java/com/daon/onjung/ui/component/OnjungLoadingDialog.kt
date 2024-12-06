package com.daon.onjung.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme


@Preview
@Composable
fun OnjungLoadingDialog (

) {
    val loadingComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading)
    )
    val toryJumpingComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.tory_jumping)
    )
    val loadingLottieAnimatable = rememberLottieAnimatable()
    val toryJumpingLottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(loadingComposition) {
        loadingLottieAnimatable.animate(
            composition = loadingComposition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f,
            iterations = LottieConstants.IterateForever
        )
    }

    LaunchedEffect(toryJumpingComposition) {
        toryJumpingLottieAnimatable.animate(
            composition = toryJumpingComposition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f,
            iterations = LottieConstants.IterateForever
        )
    }
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Black.copy(alpha = 0.7f)
            )
            .offset(y = (-60).dp)
    ) {
        LottieAnimation(
            composition = loadingComposition,
            progress = loadingLottieAnimatable.progress,
            modifier = Modifier
                .fillMaxSize(0.25f)
                .padding(top = 50.dp),
            contentScale = ContentScale.FillHeight
        )

        LottieAnimation(
            composition = toryJumpingComposition,
            progress = toryJumpingLottieAnimatable.progress,
            modifier = Modifier
                .fillMaxSize(0.35f)
                .padding(
                    end = 34.dp,
                ),
            contentScale = ContentScale.FillWidth
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=210.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                "영수증 정보를 인식중이에요.",
                style = OnjungTheme.typography.body1.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "잠시만 기다려 주세요!",
                style = OnjungTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color(0xFFB4B4B4),
            )
        }
    }
}