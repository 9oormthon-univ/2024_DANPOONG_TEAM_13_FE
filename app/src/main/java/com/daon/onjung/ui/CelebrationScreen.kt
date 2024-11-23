package com.daon.onjung.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.daon.onjung.ui.component.OnjungAnimation
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
            .statusBarsPadding()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_bg_celeb),
            contentDescription = "ic_bg_celeb",
            modifier = Modifier.fillMaxSize(),
            tint = Color.Unspecified
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        vertical = 16.dp,
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
            OnjungAnimation()

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                MealTicket(
                    name = name,
                    imageUrl = logoImgUrl,
                    address = address,
                    category = category,
                    expirationDate = expirationDate,
                    isValidate = true,
                    isButtonVisible = false
                ) {}
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (6).dp, y = (-28).dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = CircleShape,
                            clip = false
                        )
                        .clip(CircleShape)
                        .background(Color(0xFF3F3F3F))
                ) {
                    Text(
                        "무료\n식권",
                        style = OnjungTheme.typography.body1.copy(
                            fontSize = 16.sp,
                            lineHeight = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .padding(vertical = 11.dp, horizontal = 15.dp)

                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "식권은 가게에서 무료로 사용이 가능해요.",
                style = OnjungTheme.typography.caption,
                color = OnjungTheme.colors.text_3
            )
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