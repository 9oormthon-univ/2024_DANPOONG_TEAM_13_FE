package com.daon.onjung.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SupportBannerRow(
    items: List<String>,
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
                imageUrl = item
            )
        }
    }
}

