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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.daon.onjung.util.formatCurrency

@Composable
fun ShopInfoContainer(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String,
    tag: String,
    address: String,
    totalFundsRaised: Int,
    updatePeriod: String,
    totalVisitedUsers: Int,
    mealTicketUsers: Int,
    mealTicketPaymentAmount: Int,
    warmthContributionAmount: Int,
    isExpanded: Boolean = true,
    onExpandChange: (Boolean) -> Unit
) {
    Column(
        modifier = modifier.background(
            color = OnjungTheme.colors.white,
            shape = RoundedCornerShape(20.dp)
        )
    ) {
        ShopInfoHeader(
            name = name,
            imageUrl = imageUrl,
            tag = tag,
            address = address,
            totalFundsRaised = totalFundsRaised,
            updatePeriod = updatePeriod
        )
        if (isExpanded) {
            ShopInfoDetail(
                totalVisitedUsers = totalVisitedUsers,
                mealTicketUsers = mealTicketUsers,
                mealTicketPaymentAmount = mealTicketPaymentAmount,
                warmthContributionAmount = warmthContributionAmount
            )
        }
        ExpandButton(isExpanded = isExpanded) {
            onExpandChange(it)
        }
    }
}

@Composable
private fun ShopInfoHeader(
    name: String,
    imageUrl: String,
    tag: String,
    address: String,
    totalFundsRaised: Int,
    updatePeriod: String,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(
            horizontal = 20.dp,
            vertical = 24.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = OnjungTheme.colors.main_coral,
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                model = ImageRequest.Builder(context).data(imageUrl).build(),
                contentDescription = "IMG_SHOP",
                placeholder = painterResource(id = R.drawable.img_dummy)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        style = OnjungTheme.typography.h2,
                        color = OnjungTheme.colors.text_1,
                    )

                    Text(
                        text = tag,
                        style = OnjungTheme.typography.caption,
                        color = OnjungTheme.colors.text_3
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gps),
                        contentDescription = "IC_GPS",
                        tint = Color.Unspecified
                    )

                    Text(
                        text = address,
                        style = OnjungTheme.typography.body2,
                        color = OnjungTheme.colors.text_3,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        
        TotalFundraisingBox(
            totalFundsRaised = totalFundsRaised,
            updatePeriod = updatePeriod
        )
    }
}

@Composable
private fun TotalFundraisingBox(
    totalFundsRaised: Int,
    updatePeriod: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = OnjungTheme.colors.gray_3,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "모인 금액",
                style = OnjungTheme.typography.caption.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = OnjungTheme.colors.text_2
            )

            Text(
                text = formatCurrency(totalFundsRaised),
                style = OnjungTheme.typography.h2,
                color = OnjungTheme.colors.text_1
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "갱신 기간",
                style = OnjungTheme.typography.caption.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = OnjungTheme.colors.text_2
            )

            Text(
                text = updatePeriod,
                style = OnjungTheme.typography.h1,
                color = OnjungTheme.colors.main_coral
            )
        }
    }
}

@Composable
private fun ShopInfoDetail(
    totalVisitedUsers: Int,
    mealTicketUsers: Int,
    mealTicketPaymentAmount: Int,
    warmthContributionAmount: Int,
) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShopInfoChip(
                iconRes = R.drawable.ic_visitor_chip,
                label = "총 방문자 수"
            )

            Text(
                text = "$totalVisitedUsers 명",
                style = OnjungTheme.typography.body2,
                color = Color(0xFFA7A7A7)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShopInfoChip(
                iconRes = R.drawable.ic_meal_ticket_chip,
                label = "식권 이용자 수"
            )

            Text(
                text = "$mealTicketUsers 명",
                style = OnjungTheme.typography.body2,
                color = Color(0xFFA7A7A7)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShopInfoChip(
                iconRes = R.drawable.ic_receipt_chip,
                label = "식권 결제 금액"
            )

            Text(
                text = formatCurrency(mealTicketPaymentAmount),
                style = OnjungTheme.typography.body2,
                color = Color(0xFFA7A7A7)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShopInfoChip(
                iconRes = R.drawable.ic_heart_chip,
                label = "온기 전달 금액"
            )

            Text(
                text = formatCurrency(warmthContributionAmount),
                style = OnjungTheme.typography.body2,
                color = Color(0xFFA7A7A7)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ShopInfoChip(
    @DrawableRes iconRes: Int,
    label: String
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = OnjungTheme.colors.gray_2,
                shape = RoundedCornerShape(3.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 6.dp,
                vertical = 4.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "ICON",
                tint = OnjungTheme.colors.main_coral
            )

            Text(
                text = label,
                style = OnjungTheme.typography.caption,
                color = OnjungTheme.colors.text_2
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

@Preview
@Composable
fun ShopInfoContainerPreview() {
    var isExpanded by remember { mutableStateOf(false) }

    ShopInfoContainer(
        name = "한걸음 닭꼬치",
        imageUrl = "https://via.placeholder.com/150",
        tag = "일식",
        address = "송파구 오금로 533 1층 (거여동)",
        totalFundsRaised = 121800,
        updatePeriod = "D-13",
        totalVisitedUsers = 24,
        mealTicketUsers = 14,
        mealTicketPaymentAmount = 114000,
        warmthContributionAmount = 78000,
        isExpanded = isExpanded,
        onExpandChange = {
            isExpanded = it
        }
    )
}