package com.daon.onjung.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Preview(showBackground = true)
@Composable
fun HomeBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = OnjungTheme.colors.main_coral,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(
                RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "따듯한 온기를 전해요",
                    style = OnjungTheme.typography.h2.copy(
                        color = OnjungTheme.colors.white,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    "선행 동참하고 식권 당첨 확률 올리기",
                    style = OnjungTheme.typography.body2.copy(
                        color = OnjungTheme.colors.white.copy(alpha = 0.8f),
                    ),
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_home_char2),
                contentDescription = "home_character",
                tint = Color.Unspecified
            )
        }
    }
}
