package com.daon.onjung.ui.community.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.ui.community.component.CommentItem
import com.daon.onjung.ui.component.OTextField
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CommunityDetailScreen(
    appState: OnjungAppState,
    viewModel: CommunityDetailViewModel,
    postId: Int
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        viewModel.getBoardDetail(postId)

        effectFlow.collectLatest { effect ->
            when (effect) {
                is CommunityDetailContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is CommunityDetailContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.gray_2)
            .statusBarsPadding()
    ) {
        TopBar(
            "상세 보기",
            rightIcon = null,
            leftIconOnClick = {
                appState.navController.navigateUp()
            },
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                CommunityFeedItem(
                    profileImgUrl = uiState.writerInfo.profileImgUrl,
                    userName = uiState.writerInfo.maskedNickname,
                    imageUrl = uiState.boardInfo.imgUrl,
                    isLiked = uiState.boardInfo.isLiked,
                    likeCount = uiState.boardInfo.likeCount,
                    commentCount = uiState.boardInfo.commentCount,
                    title = uiState.boardInfo.title,
                    content = uiState.boardInfo.content,
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
                Spacer(modifier = Modifier.height(50.dp))
            }
        }

        CommunityDetailCommentInputSection(
            profileImageUrl = "https://avatars.githubusercontent.com/u/77449515?v=4",
            commentContent = ""
        )
    }
}

@Composable
fun CommunityFeedItem(
    profileImgUrl: String,
    userName: String,
    imageUrl: String?,
    isLiked: Boolean,
    likeCount: Int,
    commentCount: Int,
    title: String,
    content: String,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CommunityFeedProfileSection(
            profileImageUrl = profileImgUrl,
            userName = userName,
            createdAt = "9시간 전"
        )

        imageUrl?.let { uri ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
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
            title = title,
            content = content
        )

        CommunityFeedLikeAndCommentSection(
            isLiked = isLiked,
            likeCount = likeCount,
            commentCount = commentCount
        ) {

        }


    }
}

@Composable
fun CommunityFeedProfileSection(
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

@Composable
fun CommunityFeedLikeAndCommentSection(
    isLiked: Boolean,
    likeCount: Int,
    commentCount: Int,
    onLike: () -> Unit
) {
    Row(
        modifier = Modifier.padding(
            horizontal = 20.dp,
            vertical = 10.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                modifier = Modifier.clickable {
                    onLike()
                },
                painter = painterResource(id = R.drawable.ic_post_like),
                contentDescription = "ic_post_like",
                tint = OnjungTheme.colors.text_3
            )

            Text(
                "$likeCount",
                style = OnjungTheme.typography.body2,
                color = OnjungTheme.colors.text_2
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_post_comment),
                contentDescription = "ic_post_comment",
                tint = OnjungTheme.colors.text_3
            )

            Text(
                "$commentCount",
                style = OnjungTheme.typography.body2,
                color = OnjungTheme.colors.text_2
            )
        }
    }
}

@Composable
fun CommunityFeedDetailContent(
    title: String,
    content: String,
) {
    Column(
        modifier = Modifier.padding(
            horizontal = 20.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            title,
            style = OnjungTheme.typography.h1,
            color = OnjungTheme.colors.text_1
        )

        Text(
            content,
            style = OnjungTheme.typography.body1,
            color = OnjungTheme.colors.text_2
        )
    }
}

@Composable
fun CommunityDetailCommentInputSection(
    profileImageUrl: String,
    commentContent: String,
) {
    val context = LocalContext.current

    val enabled = commentContent.isNotEmpty()

    Column {
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
                contentScale = ContentScale.Crop,
                contentDescription = "IMG_PROFILE_IMAGE",
            )

            Spacer(modifier = Modifier.width(8.dp))

            OTextField(
                value = commentContent,
                onValueChange = {},
                placeholderText = "따뜻한 댓글을 남겨주세요.",
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
                contentDescription = "ic_post_comment",
                tint = Color.Unspecified
            )
        }
    }
}