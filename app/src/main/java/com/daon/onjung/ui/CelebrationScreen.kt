package com.daon.onjung.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daon.onjung.R
import com.daon.onjung.network.model.StoreCategory
import com.daon.onjung.ui.component.MealTicket
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun CelebrationScreen(
    name: String,
    logoImgUrl: String,
    address: String,
    category: StoreCategory,
    userName: String,
    expirationDate: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFFFFF0EF)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        vertical = 16.dp,
                        horizontal = 20.dp
                    )
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                "식권 당첨!",
                style = OnjungTheme.typography.h1.copy(
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFFFF563E)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "축하드려요 \uD83C\uDF89 선순환의 첫 걸음,\n${userName}님의 선행이 식권으로 돌아왔어요.",
                style = OnjungTheme.typography.body1,
                color = Color(0xFF808080),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            MealTicket(
                name = name,
                imageUrl = logoImgUrl,
                address = address,
                category = category,
                expirationDate = expirationDate,
                isValidate = true,
                isButtonVisible = false
            ) {

            }

            Spacer(modifier = Modifier.height(30.dp))

            FilledWidthButton(
                "식권 보러가기",
                modifier = Modifier.padding(bottom = 24.dp)
            ) {

            }
        }
    }
}

@Preview
@Composable
fun PreviewCelebrationScreen() {
    OnjungTheme {
        CelebrationScreen(
            name = "한걸음 닭꼬치",
            logoImgUrl = "https://via.placeholder.com/150",
            address = "서울특별시 마포구 월드컵로 212",
            category = StoreCategory.KOREAN,
            userName = "홍길동",
            expirationDate = "2021.12.31"
        )
    }

}