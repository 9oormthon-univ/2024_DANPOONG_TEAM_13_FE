package com.daon.onjung.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.home.IconData
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SupportBannerRow(
    items: List<IconData>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val scrollSpeed = 3

    LaunchedEffect(Unit) {
        while (true) {
            coroutineScope.launch {
                val currentOffset = listState.firstVisibleItemScrollOffset + scrollSpeed
                listState.scrollToItem(
                    index = listState.firstVisibleItemIndex,
                    scrollOffset = currentOffset
                )
                if (listState.firstVisibleItemIndex >= items.size) {
                    listState.scrollToItem(0, 0)
                }
            }
            delay(30)
        }
    }

    LazyRow(
        state = listState,
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = false
    ) {
        items(items.size * 3) { index ->
            val item = items[index % items.size]
            SupportBannerIcon(
                icon = item.icon,
                contentDescription = item.contentDescription,
                color = item.color
            )
        }
    }
}

// Preview Data
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

@Preview(showBackground = true)
@Composable
fun SupportBannerRowPreview() {
    OnjungTheme {
        SupportBannerRow(
            items = iconList,
        )
    }
}