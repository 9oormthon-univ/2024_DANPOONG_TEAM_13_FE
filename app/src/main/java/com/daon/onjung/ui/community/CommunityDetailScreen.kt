package com.daon.onjung.ui.community

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.R
import com.daon.onjung.ui.community.component.CommentItem
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun CommunityDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = OnjungTheme.colors.gray_2
            )
            .statusBarsPadding()
    ) {
        TopBar(
            "상세 보기",
            rightIcon = null,
            leftIconOnClick = { },
        )

        LazyColumn {
            item {
                CommunityFeedItem(
                    profileImgUrl = "https://avatars.githubusercontent.com/u/77449515?v=4",
                    userName = "김온정",
                    imageUrl = "https://avatars.githubusercontent.com/u/77449515?v=4",
                    isLiked = true,
                    likeCount = 10,
                    commentCount = 5,
                    content = "이번 주말에 놀러가기 좋은 곳 추천해주세요!",
                    createdDate = "2021.09.01"
                )
            }

            items(10) {
                CommentItem(
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                    imageUrl = "https://avatars.githubusercontent.com/u/77449515?v=4",
                    name = "김온정",
                    comment = "이번 주말에 놀러가기 좋은 곳 추천해주세요!",
                    date = "2021.09.01",
                    isMine = false
                )
            }
        }
    }
}

@Composable
fun CommunityFeedItem(
    profileImgUrl: String,
    userName: String,
    imageUrl: String,
    isLiked: Boolean,
    likeCount: Int,
    commentCount: Int,
    content: String,
    createdDate: String,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CommunityFeedProfileSection(
            profileImageUrl = profileImgUrl,
            userName = userName
        )

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            model = ImageRequest.Builder(context).data(imageUrl).build(),
            contentScale = ContentScale.Crop,
            contentDescription = "IMG_POST_IMAGE",
        )

        CommunityFeedLikeAndCommentSection(
            isLiked = isLiked,
            likeCount = likeCount,
            commentCount = commentCount
        ) {

        }

        CommunityFeedDetailContent(
            content = content,
            createdDate = createdDate
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    color = OnjungTheme.colors.gray_3
                )
        )
    }
}

@Composable
fun CommunityFeedProfileSection(
    profileImageUrl: String,
    userName: String,
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
                .fillMaxHeight()
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
    content: String,
    createdDate: String
) {
    Column(
        modifier = Modifier.padding(
            start = 20.dp,
            end = 20.dp,
            bottom = 20.dp
        )
    ) {
        Text(
            content,
            style = OnjungTheme.typography.body2,
            color = OnjungTheme.colors.text_1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            createdDate,
            style = OnjungTheme.typography.caption,
            color = OnjungTheme.colors.text_3
        )
    }
}

@Preview
@Composable
fun CommunityDetailScreenPreview() {
    OnjungTheme {
        CommunityDetailScreen()
    }
}