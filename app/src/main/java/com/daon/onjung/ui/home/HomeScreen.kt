package com.daon.onjung.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.home.component.HomeBanner
import com.daon.onjung.ui.home.component.HomeDataBanner
import com.daon.onjung.ui.home.component.HomeShopCardLazyRow
import com.daon.onjung.ui.home.component.HomeTitleText
import com.daon.onjung.ui.home.component.SupportBannerRow
import com.daon.onjung.ui.theme.OnjungTheme

val iconList = listOf(
    IconData(
        icon = R.drawable.ic_nongsim_icon,
        contentDescription = "ic_nongsim_icon",
        color = Color(0xFF222222)
    ),
    IconData(
        icon = R.drawable.ic_cj_icon,
        contentDescription = "ic_cj_icon",
    ),
    IconData(
        icon = R.drawable.ic_goorm_icon,
        contentDescription = "ic_goorm_icon",
    ),
    IconData(
        icon = R.drawable.ic_kakao_icon,
        contentDescription = "ic_kakao_icon",
        color = Color(0xFFFAE100)
    ),
    IconData(
        icon = R.drawable.ic_coupang_icon,
        contentDescription = "ic_coupang_icon",
    )
)

val shopList = listOf(
    ShopData(
        shopId = 1,
        tag = "국가 유공자",
        tagColor = Color(0xFF81A5DA),
        title = "헌신에 보답하는\n감사의 식탁",
        likeCount = "1080",
        name = "한걸음 닭꼬치",
        region = "송파구"
    ),
    ShopData(
        shopId = 2,
        tag = "결식 아동",
        tagColor = Color(0xFF83CB82),
        title = "아이들에게\n전하는 희망의 밥상",
        likeCount = "250",
        name = "보람 돈까스",
        region = "용산구"
    ),
    ShopData(
        shopId = 3,
        tag = "결식 아동",
        tagColor = Color(0xFF83CB82),
        title = "아이들에게\n전하는 희망의 밥상",
        likeCount = "250",
        name = "보람 돈까스",
        region = "용산구"
    ),
)

@Composable
internal fun HomeScreen(
    appState: OnjungAppState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        HomeBanner(
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        HomeTitleText(
            "선행의 물결 함께하기",
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        HomeShopCardLazyRow(
            shopList
        )
        Column (
            modifier = Modifier
                .background(color = OnjungTheme.colors.gray_3)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            HomeTitleText(
                "온기를 전한 기업들",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(18.dp))
            SupportBannerRow(
                iconList
            )
            Spacer(modifier = Modifier.height(26.dp))
        }
        Spacer(modifier = Modifier.height(32.dp))
        HomeDataBanner(
            timeText = "2024.10.31 00시 00분",
            warmthDeliveryCount = 1234567,
            supporterCount = 123456,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    OnjungTheme {
        val appState = rememberOnjungAppState()

        HomeScreen(
            appState = appState
        )
    }
}