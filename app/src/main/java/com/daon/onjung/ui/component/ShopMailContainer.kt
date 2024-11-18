package com.daon.onjung.ui.component

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun ShopMailContainer(
    modifier: Modifier = Modifier,
    title: String,
    name: String,
    tag: String,
    isExpanded: Boolean = true,
    onExpandChange: (Boolean) -> Unit
) {
    Column(
        modifier = modifier.background(
            color = OnjungTheme.colors.white,
            shape = RoundedCornerShape(16.dp)
        ),
    ) {
        ShopMailHeader(
            title = title,
            name = name,
            tag = tag,
            imageUrl = ""
        )
        if (isExpanded) {
            Column {
                ShopMailItem(
                    title = "모금완료",
                    description = "모금이 완료되었어요.",
                    date = "2024. 9. 31",
                    iconRes = R.drawable.ic_fundraising_complete
                )
                ShopMailItem(
                    title = "식당 전달",
                    description = "모금된 금액을 식당에 전달했어요.",
                    date = "2024. 9. 31",
                    iconRes = R.drawable.ic_restaurant_delivery,
                )
                ShopMailItem(
                    title = "식권 발송",
                    description = "온정을 나눠준 분들께\n식당이 발송 되었어요.",
                    date = "2024. 9. 31",
                    iconRes = R.drawable.ic_meal_ticket_send
                )
                ShopMailItem(
                    title = "보고",
                    description = "가게에서 음식을 나누어 주고 있어요.",
                    date = "2024. 9. 31",
                    iconRes = R.drawable.ic_report
                )
            }
        }
        ExpandButton(isExpanded = isExpanded, onClick = {
            onExpandChange(it)
        })
    }
}

@Composable
private fun ShopMailHeader(
    title: String,
    name: String,
    tag: String,
    imageUrl: String = ""
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(75.dp)
                .background(
                    color = OnjungTheme.colors.main_coral,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp)),
            model = ImageRequest.Builder(context).data(imageUrl).build(),
            contentDescription = "IMG_SHOP",
            placeholder = painterResource(id = R.drawable.img_dummy)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = OnjungTheme.typography.h2,
                color = OnjungTheme.colors.text_1,
                maxLines = 2, // 최대 2줄로 설정
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = name,
                style = OnjungTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = OnjungTheme.colors.text_2
            )
        }

        TagChip(
            modifier = Modifier
                .align(Alignment.Top)
                .offset(x = 0.dp, y = 4.dp),
            tag = tag,
            backgroundColor = OnjungTheme.colors.main_coral.copy(
                alpha = 0.1f
            ),
            textColor = OnjungTheme.colors.main_coral
        )
    }
}

@Composable
private fun ShopMailItem(
    title: String,
    description: String,
    date: String,
    @DrawableRes iconRes: Int,
) {
    Row(
        modifier = Modifier.padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .border(
                    width = 1.dp,
                    color = OnjungTheme.colors.gray_2,
                    shape = CircleShape
                )
                .background(
                    color = OnjungTheme.colors.white,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "IC_ICON",
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = OnjungTheme.colors.text_1,
                )

                Text(
                    text = date,
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = OnjungTheme.colors.gray_1
                )
            }

            Text(
                text = description,
                style = OnjungTheme.typography.caption,
                color = OnjungTheme.colors.text_3
            )
        }
    }
}

@Composable
private fun ExpandButton(
    isExpanded: Boolean,
    onClick: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .clickable {
                onClick(!isExpanded)
            },
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    color = OnjungTheme.colors.text_3
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (isExpanded) "접기" else "펼치기",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = OnjungTheme.colors.text_3,
                )

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isExpanded) {
                                R.drawable.ic_arrow_up
                            } else {
                                R.drawable.ic_arrow_down
                            }
                        ),
                        contentDescription = "IC_ARROW",
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}

@Composable
private fun TagChip(
    modifier: Modifier = Modifier,
    tag: String,
    backgroundColor: Color = OnjungTheme.colors.main_coral.copy(
        alpha = 0.1f
    ),
    textColor: Color = OnjungTheme.colors.main_coral
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            text = tag,
            style = OnjungTheme.typography.caption.copy(
                color = textColor,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            maxLines = 1
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun ShopMailItemPreview() {
    OnjungTheme {
        ShopMailItem(
            title = "모금완료",
            description = "모금이 완료되었어요.",
            date = "2024. 9. 31",
            iconRes = R.drawable.ic_fundraising_complete
        )
    }
}

@Preview
@Composable
private fun ShopMailContainerPreview() {
    var isExpanded by remember { mutableStateOf(true) }

    OnjungTheme {
        ShopMailContainer(
            title = "헌신에 보답하는 감사의 식탁",
            name = "한걸음 닭꼬치",
            tag = "동참",
            isExpanded = isExpanded,
            onExpandChange = {
                isExpanded = it
                Log.d("isExpanded", isExpanded.toString())
            }
        )
    }
}