package com.daon.onjung.ui.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.placeholder
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme
import com.daon.onjung.util.noRippleClickable

@Preview(showBackground = true)
@Composable
fun CommentItem (
    modifier: Modifier = Modifier,
    imageUrl : String = "",
    name : String = "익명1",
    date : String = "4분",
    comment : String = "댓글입니다. 댓글입니다. 댓글입니다. 댓글입니다. 댓글입니다. 댓글입니다. 댓글입니다. 댓글입니다. 댓글입니다. 댓글입니다. 댓글입니다. 댓글입니다. ",
    isMine : Boolean = true,
    onRemove : () -> Unit = {}
) {
    val context = LocalContext.current

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(27.dp))
                .background(
                    color = OnjungTheme.colors.main_coral,
                    shape = RoundedCornerShape(18.dp)
                ),
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .placeholder(R.drawable.ic_profile_icon)
                .error(R.drawable.ic_profile_icon)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column (
            modifier = Modifier.weight(1f)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    name,
                    style = OnjungTheme.typography.body2.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = OnjungTheme.colors.text_1
                    )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    date,
                    style = OnjungTheme.typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        color = OnjungTheme.colors.text_3
                    )
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                comment,
                style = OnjungTheme.typography.body2.copy(
                    fontWeight = FontWeight.Normal,
                    color = OnjungTheme.colors.text_1
                )
            )
            if (isMine) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    "삭제",
                    style = OnjungTheme.typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        color = OnjungTheme.colors.text_3
                    ),
                    modifier = Modifier.noRippleClickable(onRemove)
                )
            }
        }
    }

}