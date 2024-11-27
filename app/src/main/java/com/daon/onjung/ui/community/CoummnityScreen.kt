package com.daon.onjung.ui.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.Routes
import com.daon.onjung.ui.community.component.CommunityBanner
import com.daon.onjung.ui.community.component.CommunityTopBar
import com.daon.onjung.ui.community.component.PostCardItem
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CommunityScreen(
    appState: OnjungAppState,
    viewModel: CommunityViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val listState = rememberLazyListState()
    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is CommunityContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }
                is CommunityContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisibleItemIndex == totalItems - 1 && !uiState.isBoardsListLastPage) {
                    viewModel.processEvent(CommunityContract.Event.LoadPosts)
                }
            }
    }


    Box (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFFEEDF),
                            Color.Transparent,
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY)
                    )
                )
        ) {
            CommunityTopBar()
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
            ) {
                item {
                    CommunityBanner()
                }
                items(uiState.posts) { post ->
                    PostCardItem(
                        title = post.titleSummary,
                        content = post.contentSummary,
                        like = post.likeCount,
                        commentCount = post.commentCount,
                        imageUrl = post.imgUrl,
                        date = post.postedAgo,
                        modifier = Modifier.padding(20.dp)
                    ) {
                        viewModel.processEvent(CommunityContract.Event.SelectPost(post.id))
                    }
                }
            }
        }
        WritePostButton(
            modifier = Modifier
                .align(
                    alignment = Alignment.BottomEnd
                )
                .padding(
                    end = 20.dp,
                    bottom = 25.dp
                )
        ) {
            appState.navController.navigate(Routes.Community.WRITE)
        }
    }
}

@Composable
private fun WritePostButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .size(64.dp),
        shadowElevation = 10.dp,
        shape = CircleShape,
        color = OnjungTheme.colors.main_coral,
        onClick = onClick
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "IC_PLUS",
                tint = Color.Unspecified,
            )
        }
    }
}