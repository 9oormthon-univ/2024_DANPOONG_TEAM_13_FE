package com.daon.onjung.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.component.ShopCard
import com.daon.onjung.ui.home.ShopData
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun HomeShopCardLazyRow(
    shopList: List<ShopData>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(shopList) { shop ->
            ShopCard(
                shopId = shop.shopId,
                tag = shop.tag,
                tagColor = shop.tagColor,
                title = shop.title,
                likeCount = shop.likeCount,
                name = shop.name,
                region = shop.region,
                onClick = {
                    // 페이지 이동
                }
            )
        }
    }
}


val shopList = listOf(
    ShopData(
        shopId = 1,
        tag = "국가 유공자",
        tagColor = Color(0xFF81A5DA),
        title = "헌신에 보답하는\n감사의 식탁",
        likeCount = "100",
        name = "한걸음 닭꼬치",
        region = "송파구"
    ),
    ShopData(
        shopId = 2,
        tag = "지역 특산물",
        tagColor = Color(0xFFE57373),
        title = "산지 직송\n자연의 맛",
        likeCount = "250",
        name = "신선마켓",
        region = "강남구"
    ),
    ShopData(
        shopId = 3,
        tag = "할인 행사",
        tagColor = Color(0xFF81C784),
        title = "가성비 최고\n한끼 식사",
        likeCount = "180",
        name = "정직한 밥상",
        region = "종로구"
    )
)

@Preview
@Composable
fun HomeShopCardLazyRowPreview() {
    OnjungTheme {
        HomeShopCardLazyRow (
            shopList
        )
    }
}
