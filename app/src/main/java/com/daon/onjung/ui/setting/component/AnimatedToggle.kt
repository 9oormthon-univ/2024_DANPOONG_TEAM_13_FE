package com.daon.onjung.ui.setting.component

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.theme.OnjungTheme


@SuppressLint("UseOfNonLambdaOffsetOverload")
@Preview
@Composable
fun AnimatedToggle(
    modifier: Modifier = Modifier
) {
    var isToggled by remember { mutableStateOf(false) }

    val togglePosition by animateDpAsState(if (isToggled) 19.dp else 2.dp)
    val backgroundColor by animateColorAsState(if (isToggled) OnjungTheme.colors.main_coral else Color.Gray)

    Box(
        modifier = modifier
            .width(45.dp)
            .height(28.dp)
            .background(backgroundColor, shape = androidx.compose.foundation.shape.CircleShape)
            .clickable { isToggled = !isToggled },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .offset(x = togglePosition)
                .background(Color.White, shape = androidx.compose.foundation.shape.CircleShape)
        )
    }
}
