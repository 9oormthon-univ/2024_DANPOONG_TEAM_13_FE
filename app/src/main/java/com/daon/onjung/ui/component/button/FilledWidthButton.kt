package com.daon.onjung.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun FilledWidthButton(
    text: String = "",
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shadowElevation: Dp = 0.dp,
    onClick: () -> Unit = {}
) {
    Surface(
        shadowElevation = shadowElevation,
        shape = CircleShape,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = if (shadowElevation > 0.dp) 4.dp else 0.dp)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .height(64.dp),
            enabled = isEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isEnabled) {
                    OnjungTheme.colors.main_coral
                } else {
                    OnjungTheme.colors.gray_2
                },
                contentColor = if (isEnabled) {
                    OnjungTheme.colors.white
                } else {
                    OnjungTheme.colors.text_3
                }
            )
        ) {
            Text(
                text,
                style = OnjungTheme.typography.h2.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilledWidthButton1Preview() {
    OnjungTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FilledWidthButton(
                "동참하기",
            )
            FilledWidthButton(
                "동참하기",
                isEnabled = false,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilledWidthButton2Preview() {
    OnjungTheme {
        Row {
            FilledWidthButton(
                "동참하기",
                modifier = Modifier
                    .weight(1f)
            )

            Spacer(modifier = Modifier.width(4.dp))

            FilledWidthButton(
                "동참하기",
                modifier = Modifier
                    .weight(1f),
                isEnabled = false
            )
        }
    }
}