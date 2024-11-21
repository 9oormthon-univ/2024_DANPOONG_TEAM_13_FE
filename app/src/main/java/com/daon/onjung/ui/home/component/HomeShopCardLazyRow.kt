package com.daon.onjung.ui.home.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.daon.onjung.network.model.StoreTag
import com.daon.onjung.network.model.response.StoreOverviewInfo
import com.daon.onjung.ui.component.ShopCard

@Composable
fun HomeShopCardLazyRow(
    shopList: List<StoreOverviewInfo>,
    modifier: Modifier = Modifier,
    isLastPage: Boolean,
    onLoadMore: () -> Unit,
    navigateToShopDetail: (Int) -> Unit
) {
    val listState = rememberLazyListState()

    LazyRow(
        modifier = modifier,
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(shopList) { shop ->
            val (tag, tagColor) = when (shop.tags.first()) {
                StoreTag.PATRIOT -> {
                    "국가 유공자" to Color(0xFF81A5DA).copy(alpha = 0.8f)
                }
                StoreTag.GOOD_PRICE -> {
                    "착한 가격" to Color(0xFFF5AB67).copy(alpha = 0.8f)
                }
                StoreTag.UNDERFED_CHILD -> {
                    "결식아동" to Color(0xFF83CB82).copy(alpha = 0.8f)
                }
            }

            ShopCard(
                shopId = shop.id,
                imgUrl = shop.bannerImgUrl,
                tag = tag,
                tagColor = tagColor,
                title = shop.title,
                likeCount = shop.totalOnjungCount,
                name = shop.name,
                address = shop.address,
                onClick = {
                    navigateToShopDetail(shop.id)
                }
            )
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisibleItemIndex == totalItems - 1 && !isLastPage) {
                    Log.d("HomeShopCardLazyRow", "Load more")
                    onLoadMore()
                }
            }
    }
}