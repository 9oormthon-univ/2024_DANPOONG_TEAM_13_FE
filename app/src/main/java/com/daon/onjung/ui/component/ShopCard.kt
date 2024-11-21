package com.daon.onjung.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun ShopCard(
    shopId: Int,
    imgUrl: String,
    tag: String,
    tagColor: Color,
    title: String,
    likeCount: Int,
    name: String,
    address: String,
    onClick: (Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .width(236.dp),
        onClick = { onClick(shopId) },
        color = OnjungTheme.colors.white,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier.aspectRatio(1.3f),
            ) {
                AsyncImage(
                    modifier = Modifier.aspectRatio(1.3f),
                    model = imgUrl,
                    contentDescription = "IMG_SHOP",
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.3f)
                        .background(
                            color = OnjungTheme.colors.text_1.copy(
                                alpha = 0.5f
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.align(Alignment.End),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_heart),
                            contentDescription = "IC_HEART",
                            tint = OnjungTheme.colors.white
                        )

                        Text(
                            "${likeCount}명의 온기",
                            style = OnjungTheme.typography.caption.copy(
                                color = OnjungTheme.colors.white
                            ),
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = tagColor,
                                    shape = CircleShape
                                )
                                .padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                )
                        ) {
                            Text(
                                tag,
                                style = OnjungTheme.typography.caption.copy(
                                    color = OnjungTheme.colors.white
                                ),
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            title,
                            style = OnjungTheme.typography.title.copy(
                                color = OnjungTheme.colors.white
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Row(
                modifier= Modifier
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = OnjungTheme.colors.text_2
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(20.dp)
                        .background(
                            color = Color(0xFFD9D9D9)
                        )
                )

                Text(
                    text = address,
                    style = OnjungTheme.typography.caption.copy(
                        color = OnjungTheme.colors.text_3
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun ShopCardPreview() {
    OnjungTheme {
        ShopCard(
            shopId = 1,
            imgUrl = "",
            tag = "장애우",
            tagColor = Color(0xFF81A5DA),
            title = "헌신에 보답하는\n감사의 식탁",
            likeCount = 100,
            name = "한걸음 닭꼬치",
            address = "송파구",
            onClick = {}
        )
    }
}