package com.daon.onjung.ui.community.detail

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.network.model.CommunityPostStatus
import com.daon.onjung.ui.community.component.CommentItem
import com.daon.onjung.ui.community.component.HeartAnimation
import com.daon.onjung.ui.component.OTextField
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommunityDetailScreen(
    appState: OnjungAppState,
    viewModel: CommunityDetailViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val listState = rememberLazyListState()

    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedProgress.snapTo(0f)
        animatedProgress.animateTo(uiState.boardInfo.likeCount / uiState.boardInfo.goalCount.toFloat(), tween(durationMillis = 1000))
    }

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is CommunityDetailContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is CommunityDetailContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }

                is CommunityDetailContract.Effect.ScrollToLastItem -> {
                    val totalItemCount = listState.layoutInfo.totalItemsCount
                    listState.scrollToItem(totalItemCount - 1)
                }
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                val totalItemCount = listState.layoutInfo.totalItemsCount
                if (lastVisibleIndex != null && lastVisibleIndex >= totalItemCount - 1) {
                    viewModel.processEvent(CommunityDetailContract.Event.LoadMoreCommentList)
                }
            }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .statusBarsPadding()
        ) {
            TopBar(
                "상세 보기",
                rightIcon = null,
                leftIconOnClick = {
                    appState.navController.navigateUp()
                },
            )

            if (
                uiState.boardInfo.status != CommunityPostStatus.IN_PROGRESS &&
                uiState.boardInfo.status != CommunityPostStatus.EXPIRED
            ) {
                CommunityDetailStatusBar(
                    status = uiState.boardInfo.status
                )
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                state = listState
            ) {
                item {
                    CommunityFeedItem(
                        profileImgUrl = uiState.writerInfo.profileImgUrl,
                        userName = uiState.writerInfo.maskedNickname,
                        imageUrl = uiState.boardInfo.imgUrl,
                        goalCount = uiState.boardInfo.goalCount,
                        likeCount = uiState.boardInfo.likeCount,
                        startDate = uiState.boardInfo.startDate,
                        endDate = uiState.boardInfo.endDate,
                        progress = animatedProgress.value,
                        commentCount = uiState.boardInfo.commentCount,
                        title = uiState.boardInfo.title,
                        content = uiState.boardInfo.content,
                        status = uiState.boardInfo.status,
                        postedAgo = uiState.boardInfo.postedAgo,
                        remainingDays = uiState.boardInfo.remainingDays
                    )
                }

                items(uiState.commentList) { comment ->
                    CommentItem(
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        ),
                        imageUrl = comment.writerInfo.profileImgUrl,
                        name = comment.writerInfo.maskedNickname,
                        comment = comment.commentInfo.content,
                        date = comment.commentInfo.postedAgo,
                        isMine = comment.writerInfo.isMe
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }

            CommunityDetailCommentInputSection(
                profileImageUrl = uiState.writerInfo.profileImgUrl,
                commentContent = uiState.commentInput,
                onContentChange = viewModel::updateCommentInput,
                onPostComment = {
                    viewModel.processEvent(CommunityDetailContract.Event.PostComment)
                }
            )
        }

        RecommendButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 20.dp,
                    bottom = 80.dp
                ),
            enabled = !uiState.boardInfo.isLiked
        ) {
            viewModel.processEvent(CommunityDetailContract.Event.ToggleLike)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CommunityFeedItem(
    profileImgUrl: String,
    userName: String,
    imageUrl: String?,
    likeCount: Int,
    goalCount: Int,
    startDate: String,
    endDate: String,
    progress: Float,
    commentCount: Int,
    title: String,
    content: String,
    status: CommunityPostStatus,
    postedAgo: String,
    remainingDays: Int
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CommunityFeedProfileSection(
            profileImageUrl = profileImgUrl,
            userName = userName,
            createdAt = postedAgo
        )

        Text(
            title,
            modifier = Modifier.padding(start = 20.dp, bottom = 12.dp),
            style = OnjungTheme.typography.h1,
            color = OnjungTheme.colors.text_1
        )

        imageUrl?.let { uri ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.88f)
                    .padding(
                        start = 20.dp, end = 20.dp,
                        bottom = 20.dp
                    )
                    .clip(RoundedCornerShape(12.dp)),
                model = ImageRequest.Builder(context).data(uri).build(),
                contentScale = ContentScale.Crop,
                contentDescription = "IMG_POST_IMAGE",
            )
        }

        CommunityFeedDetailContent(
            progress = progress,
            likeCount = likeCount,
            goalCount = goalCount,
            status = status,
            startDate = startDate,
            endDate = endDate,
            content = content,
            remainingDays = remainingDays
        )

        CommunityFeedLikeAndCommentSection(
            commentCount = commentCount
        )
    }
}

@Composable
private fun CommunityDetailStatusBar(
    status: CommunityPostStatus
) {
    val backgroundColor = when (status) {
        CommunityPostStatus.UNDER_REVIEW -> OnjungTheme.colors.sub_yellow
        CommunityPostStatus.REGISTERED -> OnjungTheme.colors.main_coral
        CommunityPostStatus.REGISTRATION_FAILED -> OnjungTheme.colors.gray_3
        else -> Color.White
    }

    val iconColor = when (status) {
        CommunityPostStatus.UNDER_REVIEW -> OnjungTheme.colors.text_2
        CommunityPostStatus.REGISTERED -> Color.White
        CommunityPostStatus.REGISTRATION_FAILED -> OnjungTheme.colors.text_2
        else -> Color.White
    }

    val statusText = when (status) {
        CommunityPostStatus.UNDER_REVIEW -> "현재 검토중인 가게예요."
        CommunityPostStatus.REGISTERED -> "선행 가게로 등록이 완료되었습니다."
        CommunityPostStatus.REGISTRATION_FAILED -> "아쉽게도 등록되지 않았어요."
        else -> ""
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor
            )
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = "ic_notification",
            tint = iconColor
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            statusText,
            style = OnjungTheme.typography.body2,
            color = iconColor
        )
    }
}


@Composable
private fun CommunityFeedProfileSection(
    profileImageUrl: String,
    userName: String,
    createdAt: String
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(context).data(profileImageUrl).build(),
            placeholder = painterResource(id = R.drawable.ic_profile_icon),
            error = painterResource(id = R.drawable.ic_profile_icon),
            contentScale = ContentScale.Crop,
            contentDescription = "IMG_PROFILE_IMAGE",
        )

        Text(
            userName,
            style = OnjungTheme.typography.body2.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = OnjungTheme.colors.text_1
        )

        Text(
            createdAt,
            style = OnjungTheme.typography.caption,
            color = OnjungTheme.colors.text_3
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CommunityDetailProgressBarSection(
    modifier: Modifier = Modifier,
    status: CommunityPostStatus,
    progress: Float,
    likeCount: Int,
    goalCount: Int,
    startDate: String,
    endDate: String,
    remainingDays: Int
) {
    val barColor = when (status) {
        CommunityPostStatus.IN_PROGRESS -> OnjungTheme.colors.main_coral
        else -> OnjungTheme.colors.gray_1
    }

    Column(
        modifier = modifier.padding(
            vertical = 10.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_heart),
                modifier = Modifier.size(30.dp),
                contentDescription = "ic_heart",
                tint = barColor
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                "$likeCount",
                style = OnjungTheme.typography.h1,
                color = OnjungTheme.colors.text_1
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                "추천",
                style = OnjungTheme.typography.body2,
                color = OnjungTheme.colors.text_3
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "목표 $goalCount",
                style = OnjungTheme.typography.body2,
                color = OnjungTheme.colors.text_3
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(
                        color = Color(0xFFEAEAEA),
                        shape = CircleShape
                    )
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(10.dp)
                    .background(
                        color = barColor,
                        shape = CircleShape
                    )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                "기간 $startDate~$endDate",
                style = OnjungTheme.typography.caption,
                color = OnjungTheme.colors.text_3
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "D-$remainingDays",
                style = OnjungTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = OnjungTheme.colors.text_1
            )
        }
    }
}

@Composable
private fun CommunityFeedLikeAndCommentSection(
    commentCount: Int
) {
    Column {
        Row(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 10.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_post_comment),
                    contentDescription = "ic_post_comment",
                    tint = OnjungTheme.colors.text_3
                )

                Text(
                    "댓글",
                    style = OnjungTheme.typography.body2,
                    color = OnjungTheme.colors.text_3
                )

                Text(
                    "$commentCount",
                    style = OnjungTheme.typography.body2,
                    color = OnjungTheme.colors.text_3
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(OnjungTheme.colors.gray_3)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun CommunityFeedDetailContent(
    progress: Float,
    likeCount: Int,
    goalCount: Int,
    status: CommunityPostStatus,
    startDate: String,
    endDate: String,
    content: String,
    remainingDays: Int
) {
    Column(
        modifier = Modifier.padding(
            horizontal = 20.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CommunityDetailProgressBarSection(
            status = status,
            progress = progress,
            likeCount = likeCount,
            goalCount = goalCount,
            startDate = startDate,
            endDate = endDate,
            remainingDays = remainingDays
        )

        Text(
            content,
            style = OnjungTheme.typography.body1,
            color = OnjungTheme.colors.text_2
        )
    }
}

@Composable
private fun CommunityDetailCommentInputSection(
    profileImageUrl: String,
    commentContent: String,
    onContentChange: (String) -> Unit,
    onPostComment: () -> Unit
) {
    val context = LocalContext.current

    val enabled = commentContent.isNotEmpty()

    Column(modifier = Modifier.imePadding()) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    color = OnjungTheme.colors.gray_1
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(context).data(profileImageUrl).build(),
                placeholder = painterResource(id = R.drawable.ic_profile_icon),
                error = painterResource(id = R.drawable.ic_profile_icon),
                contentScale = ContentScale.Crop,
                contentDescription = "IMG_PROFILE_IMAGE",
            )

            Spacer(modifier = Modifier.width(8.dp))

            OTextField(
                value = commentContent,
                onValueChange = onContentChange,
                placeholderText = "따뜻한 응원의 댓글을 남겨주세요.",
                modifier = Modifier.weight(1f),
                contentPaddingValues = PaddingValues(vertical = 18.dp),
                textStyle = OnjungTheme.typography.body2,
                textColor = OnjungTheme.colors.text_1,
                hintColor = OnjungTheme.colors.text_3,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = if (enabled) {
                    R.drawable.ic_comment_upload_enabled
                } else {
                    R.drawable.ic_comment_upload_disabled
                }),
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        if (enabled) {
                            onPostComment()
                        }
                    },
                contentDescription = "ic_post_comment",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
private fun RecommendButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    var isPlaying by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .size(width = 80.dp, height = 140.dp)
    ) {
        HeartAnimation(
            modifier = Modifier.offset(y = (-40).dp),
            isPlaying = isPlaying,
            onAnimationEnd = { isPlaying = false }
        )
        Surface(
            modifier = Modifier
                .size(80.dp),
            shadowElevation = 10.dp,
            shape = CircleShape,
            color = if (enabled) OnjungTheme.colors.white else OnjungTheme.colors.main_coral,
            onClick = {
                isPlaying = true
                if (enabled) onClick() else Toast.makeText(
                    context,
                    "이미 추천한 게시글이에요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "IC_HEART",
                    tint = if (enabled) OnjungTheme.colors.main_coral else Color.White,
                    modifier = Modifier.offset(0.dp, (-3).dp)
                )
                Text(
                    modifier = Modifier.offset(0.dp, (-4).dp),
                    text = if (enabled) "추천하기" else "추천 완료",
                    style = OnjungTheme.typography.body2.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (enabled) OnjungTheme.colors.main_coral else Color.White
                    ),
                )
            }
        }
    }
}