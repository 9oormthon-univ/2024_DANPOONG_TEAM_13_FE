package com.daon.onjung.ui.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.theme.OnjungTheme


@Preview(showBackground = true)
@Composable
fun HomeTitleText(
    text : String = "선행의 물결 함께하기",
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding( vertical = 6.dp)
    ) {
        Text(
            text,
            style = OnjungTheme.typography.h2.copy(
                fontWeight = FontWeight.Bold,
                color = OnjungTheme.colors.text_1
            )

        )
    }
}