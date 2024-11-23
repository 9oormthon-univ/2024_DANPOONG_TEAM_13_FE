package com.daon.onjung.ui.donation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme


@Composable
fun DonationSelectPrice (
    price : Int = 10000,
    modifier : Modifier = Modifier,
    onPriceClick : (Int) -> Unit = {},
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .background(color = OnjungTheme.colors.white)
            .padding(vertical = 28.dp, horizontal = 20.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "동참 금액",
                style = OnjungTheme.typography.body1.copy(
                    color = OnjungTheme.colors.text_1,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "$price 원",
                    style = OnjungTheme.typography.h2.copy(
                        color = OnjungTheme.colors.text_1,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_x_button),
                    contentDescription = "X Button",
                    tint = Color.Unspecified
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DonationSelectPriceButton(1) {
                onPriceClick(10000)
            }
            DonationSelectPriceButton(3) {
                onPriceClick(30000)
            }
            DonationSelectPriceButton(5) {
                onPriceClick(50000)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DonationSelectPriceButton(10) {
                onPriceClick(100000)
            }
            DonationSelectPriceButton(50) {
                onPriceClick(500000)
            }
            DonationSelectPriceButton(100) {
                onPriceClick(1000000)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "동참은 1만원 단위로 가능합니다.",
            style = OnjungTheme.typography.caption.copy(
                color = OnjungTheme.colors.text_3,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview
@Composable
private fun DonationSelectPriceButton (
    price : Int = 1,
    onClick : () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(101.dp)
            .border(width = 1.dp, color = OnjungTheme.colors.gray_2, shape = RoundedCornerShape(6.dp))
            .clickable{ onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            "+ ${price}만원",
            style = OnjungTheme.typography.body2.copy(
                color = OnjungTheme.colors.text_2,
            ),
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DonationSelectPricePreview () {
    OnjungTheme {
        DonationSelectPrice()
    }
}