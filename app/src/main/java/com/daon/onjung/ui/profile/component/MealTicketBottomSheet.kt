package com.daon.onjung.ui.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
internal fun MealTicketBottomSheet(
    name: String,
    address: String,
    expirationDate: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFEEDF),
                        Color.White,
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                ),
                shape = RoundedCornerShape(
                    topStart = 26.dp,
                    topEnd = 26.dp
                )
            )
    ) {
        Icon(
            modifier = Modifier
                .width(49.dp)
                .align(Alignment.TopEnd)
                .padding(end = 20.dp, top = 20.dp),
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "IC_APP_LOGO",
            tint = Color.Unspecified
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                "1만원 금액권",
                style = OnjungTheme.typography.h2.copy(
                    fontWeight = FontWeight.Bold,
                    color = OnjungTheme.colors.text_1
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "사장님께 QR코드를 보여주세요!",
                style = OnjungTheme.typography.h2.copy(
                    fontWeight = FontWeight.Bold,
                    color = OnjungTheme.colors.text_1
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .background(OnjungTheme.colors.white)
                    .padding(6.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dummy_qr_code),
                    contentDescription = "IC_DUMMY_QR_CODE",
                    tint = Color.Unspecified
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            MealTicketSummary(
                name = name,
                address = address,
                expirationDate = expirationDate
            )

            Spacer(modifier = Modifier.height(24.dp))

            FilledWidthButton(
                text = "사진으로 저장하기",
                modifier = Modifier.padding(bottom = 40.dp)
            ) {

            }
        }
    }
}

@Composable
private fun MealTicketSummary(
    name: String,
    address: String,
    expirationDate: String
) {
    Box(
        modifier = Modifier.background(
            color = OnjungTheme.colors.gray_3,
            shape = RoundedCornerShape(10.dp)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_store),
                    contentDescription = "IC_SHOP",
                    tint = OnjungTheme.colors.main_coral
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "가게명",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = OnjungTheme.colors.text_1
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = name,
                    style = OnjungTheme.typography.body1,
                    color = OnjungTheme.colors.text_2
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_gps),
                    contentDescription = "IC_GPS",
                    tint = OnjungTheme.colors.main_coral
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "위치",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = OnjungTheme.colors.text_1
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = address,
                    style = OnjungTheme.typography.body1,
                    color = OnjungTheme.colors.text_2
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "IC_CALENDAR",
                    tint = OnjungTheme.colors.main_coral
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "유효기간",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = OnjungTheme.colors.text_1
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = expirationDate,
                    style = OnjungTheme.typography.body1,
                    color = OnjungTheme.colors.text_2
                )
            }
        }
    }
}

@Preview
@Composable
internal fun MealTicket() {
    MealTicketBottomSheet(
        name = "한걸음 닭꼬치",
        address = "송파구 오금로 533 1층 (거여동)",
        expirationDate = "2024년 11월 30일"
    )
}