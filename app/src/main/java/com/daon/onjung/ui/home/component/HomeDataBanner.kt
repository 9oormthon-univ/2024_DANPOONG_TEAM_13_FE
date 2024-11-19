package com.daon.onjung.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme
import java.text.DecimalFormat

private fun formatNumber(number: Int): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(number)
}

@Preview(showBackground = true)
@Composable
fun HomeDataBanner(
    timeText: String = "2024.10.31 00시 00분",
    warmthDeliveryCount: Int = 1234567,
    supporterCount: Int = 123456,
    modifier: Modifier = Modifier,
) {
    val warmthDeliveryCountFormat = formatNumber(warmthDeliveryCount)
    val supporterCountFormat = formatNumber(supporterCount)

    Surface(
        shadowElevation = 10.dp,
        color = OnjungTheme.colors.main_coral,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .size(width = 320.dp, height = 170.dp)
    ) {
        Column (
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp, top = 9.dp,end = 19.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column () {
                    Spacer(modifier = Modifier.height(11.dp))
                    Text(
                        "온정과 함께 한 선순환",
                        style = OnjungTheme.typography.h2.copy(
                            fontWeight = FontWeight.Bold,
                            color = OnjungTheme.colors.white
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "$timeText 기준",
                        style = OnjungTheme.typography.caption.copy(
                            fontWeight = FontWeight.Normal,
                            color = OnjungTheme.colors.white.copy(alpha = 0.8f),
                        )
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_home_char),
                    contentDescription = "home_character",
                    tint = Color.Unspecified
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    )
                    .clip(RoundedCornerShape(10.dp)) // 모서리 반경 설정
                    .background(color = OnjungTheme.colors.white),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp, vertical = 2.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "온기 전달 횟수",
                        style = OnjungTheme.typography.body2.copy(
                            color = OnjungTheme.colors.text_1
                        )
                    )

                    Text(
                        "$warmthDeliveryCountFormat 건",
                        style = OnjungTheme.typography.body2.copy(
                            color = OnjungTheme.colors.text_1,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp, vertical = 2.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        "함께한 온정인 수",
                        style = OnjungTheme.typography.body2.copy(
                            color = OnjungTheme.colors.text_1,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        "$supporterCountFormat 명",
                        style = OnjungTheme.typography.body2.copy(
                            color = OnjungTheme.colors.text_1,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}