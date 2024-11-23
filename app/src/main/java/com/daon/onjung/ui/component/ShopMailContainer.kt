package com.daon.onjung.ui.component

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.R
import com.daon.onjung.network.model.OnjungMailStatus
import com.daon.onjung.network.model.OnjungType
import com.daon.onjung.ui.theme.OnjungTheme
import kotlin.random.Random

@Composable
fun ShopMailContainer(
    modifier: Modifier = Modifier,
    shopId: Int,
    imageUrl: String,
    title: String,
    name: String,
    date: String,
    type: OnjungType,
    status: OnjungMailStatus,
    eventPeriod: String?,
    storeDeliveryDate: String?,
    ticketIssueDate: String?,
    reportDate: String?,
    onClick: (Int) -> Unit
) {
    var isExpanded by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.background(
            color = OnjungTheme.colors.white,
            shape = RoundedCornerShape(20.dp)
        ).clickable {
            onClick(shopId)
        },
    ) {
        ShopMailHeader(
            title = title,
            name = name,
            type = type,
            imageUrl = imageUrl,
            date = date
        )

        if (isExpanded) {
            Column {
                if(type == OnjungType.DONATION) {
                    if (status == OnjungMailStatus.IN_PROGRESS) {
                        ShopMailItem(
                            title = "모금중",
                            description = "따뜻한 온기가 하나 둘 모이고 있어요.",
                            date = eventPeriod ?: "",
                            iconRes = R.drawable.ic_fundraising_complete
                        )
                    } else if (
                        status == OnjungMailStatus.COMPLETED ||
                        status == OnjungMailStatus.STORE_DELIVERY ||
                        status == OnjungMailStatus.TICKET_ISSUE ||
                        status == OnjungMailStatus.REPORT
                    ) {
                        ShopMailItem(
                            title = "모금 완료",
                            description = "온기가 모여 결식 아동과 장애우에게 따뜻한\n한 끼가 전해졌습니다.",
                            date = eventPeriod ?: "",
                            iconRes = R.drawable.ic_fundraising_complete
                        )
                    }

                    if (
                        status == OnjungMailStatus.STORE_DELIVERY ||
                        status == OnjungMailStatus.TICKET_ISSUE ||
                        status == OnjungMailStatus.REPORT
                    ) {
                        ShopMailItem(
                            title = "식당 전달",
                            description = "모금 금액은 식당에 안전하게 전달되었습니다.",
                            date = storeDeliveryDate ?: "",
                            iconRes = R.drawable.ic_restaurant_delivery,
                        )
                    }

                    if (
                        status == OnjungMailStatus.TICKET_ISSUE ||
                        status == OnjungMailStatus.REPORT
                    ) {
                        ShopMailItem(
                            title = "식권 발송",
                            description = "온정인 ${Random.nextInt(30, 50)}에게 식권이 전달되어 더 큰 선한 영향력을 만들었어요!",
                            date = ticketIssueDate ?: "",
                            iconRes = R.drawable.ic_meal_ticket_send
                        )
                    }

                    if (
                        status == OnjungMailStatus.REPORT
                    ) {
                        ShopMailItem(
                            title = "보고",
                            description = "결식 아동 35명께 식사를 전하며 동참 금액 이상의 가치를 나눴어요. 감사합니다!",
                            date = reportDate ?: "",
                            iconRes = R.drawable.ic_report
                        )
                    }
                }
            }
        }
        if (status != OnjungMailStatus.IN_PROGRESS && status != OnjungMailStatus.COMPLETED) {
            ExpandButton(isExpanded = isExpanded, onClick = {
                isExpanded = it
            })
        }
    }
}

@Composable
private fun ShopMailHeader(
    title: String,
    name: String,
    type: OnjungType,
    imageUrl: String,
    date: String
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(93.dp)
                .background(
                    color = OnjungTheme.colors.main_coral,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp)),
            model = ImageRequest.Builder(context).data(imageUrl).build(),
            contentDescription = "IMG_SHOP",
            placeholder = painterResource(id = R.drawable.img_dummy),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                date,
                style = OnjungTheme.typography.caption.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = OnjungTheme.colors.text_3
                ),
            )

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
            type = type
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
                    style = OnjungTheme.typography.caption,
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
                    color = Color(0xFFD9D9D9)
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
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
    type: OnjungType
) {
    val (tag, tagColor) = when (type) {
        OnjungType.DONATION -> "동참" to Color(0xFFFF7B69)
        OnjungType.SHARE -> "공유" to Color(0xFF5CA956)
        OnjungType.RECEIPT -> "방문" to Color(0xFF698AFF)
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

@Preview(
    showBackground = true,
)
@Composable
private fun ShopMailItemPreview() {
    OnjungTheme {
        ShopMailItem(
            title = "모금완료",
            description = "따뜻한 온기가 하나 둘 모이고 있어요.",
            date = "2024. 9. 31",
            iconRes = R.drawable.ic_fundraising_complete
        )
    }
}

@Preview
@Composable
private fun ShopMailContainerPreview() {
    OnjungTheme {
        ShopMailContainer(
            title = "헌신에 보답하는 감사의 식탁",
            shopId = 1,
            imageUrl = "",
            name = "한걸음 닭꼬치",
            type = OnjungType.DONATION,
            status = OnjungMailStatus.COMPLETED,
            eventPeriod = "2024. 9. 10",
            storeDeliveryDate = null,
            ticketIssueDate = null,
            reportDate = null,
            date = "2024년 11월 22일"
        ) {

        }
    }
}