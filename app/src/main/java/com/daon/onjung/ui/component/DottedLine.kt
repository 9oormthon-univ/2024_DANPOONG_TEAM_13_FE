package com.daon.onjung.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun VerticalDottedLine(
    modifier: Modifier = Modifier,
    color: Color = OnjungTheme.colors.text_3,
    lineLength: Dp = 5.dp, // 그려질 선의 길이
    interval: Dp = 3.dp // 공백의 길이
) {
    Canvas(modifier = modifier) {
        val totalHeight = size.height
        val lineLengthPx = lineLength.toPx()
        val intervalPx = interval.toPx()

        var y = 0f
        while (y < totalHeight) {
            drawLine(
                color = color,
                start = Offset(x = size.width / 2, y = y),
                end = Offset(x = size.width / 2, y = y + lineLengthPx),
                strokeWidth = size.width
            )
            y += lineLengthPx + intervalPx
        }
    }
}

@Composable
fun HorizontalDottedLine(
    modifier: Modifier = Modifier,
    color: Color = OnjungTheme.colors.text_3,
    lineLength: Dp = 5.dp, // 그려질 선의 길이
    interval: Dp = 3.dp // 공백의 길이
) {
    Canvas(modifier = modifier) {
        val totalWidth = size.width
        val lineLengthPx = lineLength.toPx()
        val intervalPx = interval.toPx()

        var x = 0f
        while (x < totalWidth) {
            drawLine(
                color = color,
                start = Offset(x = x, y = size.height / 2),
                end = Offset(x = x + lineLengthPx, y = size.height / 2),
                strokeWidth = size.height
            )
            x += lineLengthPx + intervalPx
        }
    }
}


