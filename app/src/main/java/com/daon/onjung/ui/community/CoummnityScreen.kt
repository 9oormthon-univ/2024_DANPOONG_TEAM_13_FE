package com.daon.onjung.ui.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.Routes
import com.daon.onjung.ui.community.component.PostCardItem
import com.daon.onjung.ui.component.TopBar
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
        viewModel.processEvent(CommunityContract.Event.RefreshPosts)
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
                    viewModel.processEvent(CommunityContract.Event.LoadMorePosts)
                }
            }
    }


    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.white)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            TopBar(
                "가게 추천",
                leftIcon = null,
                rightIcon = null
            )
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
            ) {
                items(uiState.posts) { post ->
                    PostCardItem(
                        title = post.titleSummary,
                        content = post.contentSummary,
                        like = post.likeCount,
                        commentCount = post.commentCount,
                        imageUrl = post.imgUrl,
                        status = post.status,
                        date = post.postedAgo,
                        modifier = Modifier
                    ) {
                        viewModel.processEvent(CommunityContract.Event.SelectPost(post.id))
                    }
                    Divider(
                        color = OnjungTheme.colors.gray_2,
                        modifier = Modifier
                            .fillMaxHeight()
                            .height(1.dp)
                    )
                }
            }
        }
        WritePostButton(
            modifier = Modifier
                .align(
                    alignment = Alignment.BottomCenter
                )
                .padding(
                    bottom = 24.dp
                )
        ) {
            appState.navigate(Routes.Community.WRITE)
        }
    }
}

@Composable
private fun WritePostButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        shadowElevation = 10.dp,
        shape = CircleShape,
        color = Color(0xFF4E4E4E),
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 20.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pencil),
                contentDescription = "IC_PENCIL",
                tint = Color.Unspecified,
            )
            Text(
                "선행가게 알리기",
                style = OnjungTheme.typography.body1.copy(
                    color = OnjungTheme.colors.white,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}