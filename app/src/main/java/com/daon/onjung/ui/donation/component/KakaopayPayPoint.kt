package com.daon.onjung.ui.donation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.theme.OnjungTheme

@Preview(showBackground = true)
@Composable
fun KakaopayPayPoint (
    point: Int = 13,
    modifier: Modifier = Modifier,
    onUseClick: () -> Unit = {},
) {
    Surface(
        shadowElevation = 5.dp,
        color = Color.White,
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(
                start = 15.dp,
                end = 23.dp,
                top = 13.dp,
                bottom = 13.dp
            ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "페이포인트",
                style = OnjungTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(end = 26.dp)
            )
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = OnjungTheme.colors.gray_3)
                    .weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "보유 ${point}P",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        color = OnjungTheme.colors.text_3
                    ),
                    modifier = Modifier.padding(end = 5.dp)
                )
                
                Text(
                    "모두 사용",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = OnjungTheme.colors.text_3
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .border(width = 1.dp, color = OnjungTheme.colors.gray_3, shape = RoundedCornerShape(5.dp))
                        .background(color = OnjungTheme.colors.white)
                        .padding(vertical = 10.5.dp, horizontal = 7.5.dp)
                        .clickable { onUseClick() }
                )
            }
        }
    }
}