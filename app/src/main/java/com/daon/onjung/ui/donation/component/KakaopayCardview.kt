package com.daon.onjung.ui.donation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Preview(showBackground = true)
@Composable
fun KakaopayCardview(
    money: Int = 10000,
    modifier: Modifier = Modifier,
) {
    Surface(
        shadowElevation = 5.dp,
        color = Color.White,
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier.padding(
                vertical = 17.dp
            ),
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xFFFFEB00))
                    .padding(18.dp)
                    .height(125.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_kakaopay),
                        contentDescription = "Kakaopay Icon",
                        tint = Color.Black,
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        "머니",
                        style = OnjungTheme.typography.title.copy(
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ){
                    Text(
                        "현재잔액",
                        style = OnjungTheme.typography.caption.copy(
                            fontWeight = FontWeight.Normal,
                            color = OnjungTheme.colors.text_2
                        )
                    )
                    Text(
                        "10,000원",
                        style = OnjungTheme.typography.title.copy(
                            fontWeight = FontWeight.Normal,
                            color = OnjungTheme.colors.text_1
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "카카오페이머니",
                    style = OnjungTheme.typography.caption.copy(
                        color = OnjungTheme.colors.text_2
                    )
                )
            }
            Spacer(modifier = Modifier.height(15.5.dp))
            Divider(
                color = Color(0xFFEFF2F4),
                thickness = 1.dp,
            )
            Spacer(modifier = Modifier.height(14.5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    "충전계좌",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF636B73)
                    )
                )
                Text(
                    "우리  ***4066",
                    style = OnjungTheme.typography.caption.copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF868E96)
                    )
                )
            }
        }


    }
}