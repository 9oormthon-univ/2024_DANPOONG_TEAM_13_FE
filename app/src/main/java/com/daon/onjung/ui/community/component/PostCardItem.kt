package com.daon.onjung.ui.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.placeholder
import com.daon.onjung.R
import com.daon.onjung.network.model.CommunityPostStatus
import com.daon.onjung.ui.theme.OnjungTheme
import com.daon.onjung.util.noRippleClickable


@Preview(showBackground = true)
@Composable
fun PostCardItem (
    title : String = "신사동 역전할맥 추천",
    content : String = "여기 역저할맥 진짜 맛있는데 시장님이 선한 일도 하시네요 도움이 필요하신 분들 여기로! 여기 역저할맥 진짜 맛있는데 시장님이 선한 일도 하시네요 도움이 필요하신 분들 여기로!",
    like : Int = 3,
    commentCount : Int = 10,
    status: CommunityPostStatus = CommunityPostStatus.EXPIRED,
    imageUrl : String = "",
    date : String = "3시간 전",
    modifier: Modifier = Modifier,
    onClick : () -> Unit = {}
) {
    val context = LocalContext.current
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .noRippleClickable(onClick),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TagChip(
                status = status,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                style = OnjungTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF414141)
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(53.dp))
            Text(
                text = date,
                style = OnjungTheme.typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFFB3B3B3)
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    content,
                    style = OnjungTheme.typography.body2.copy(
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF7F7F7F)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_community_like),
                        contentDescription = "ic_post_like",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "$like",
                        style = OnjungTheme.typography.caption.copy(
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF939393)
                        )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_community_comment),
                        contentDescription = "ic_post_like",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "$commentCount",
                        style = OnjungTheme.typography.caption.copy(
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF939393)
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(32.dp))
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        color = OnjungTheme.colors.main_coral,
                        shape = RoundedCornerShape(10.dp)
                    ),
                model = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .placeholder(R.drawable.img_dummy)
                    .error(R.drawable.img_dummy)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )

        }
    }
}


@Composable
private fun TagChip(
    modifier: Modifier = Modifier,
    status: CommunityPostStatus
) {
    val (tag, tagColor) = when (status) {
        CommunityPostStatus.IN_PROGRESS -> "진행중" to Color(0xFFFF7B69)
        else -> "만료" to OnjungTheme.colors.text_2
    }

    Box(
        modifier = modifier
            .background(
                color = tagColor.copy(alpha = 0.1f),
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            text = tag,
            style = OnjungTheme.typography.caption.copy(
                color = tagColor,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            maxLines = 1
        )
    }

}