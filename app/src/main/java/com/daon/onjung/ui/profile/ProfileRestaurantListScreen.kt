package com.daon.onjung.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.theme.OnjungTheme
import com.daon.onjung.util.formatCurrency

data class ProfileRestaurant(
    val imageUrl: String,
    val tag: String,
    val tagColor: Color,
    val name: String,
    val description: String,
    val warmthAmount: Int,
    val date: String
)

private val restaurants = listOf(
    ProfileRestaurant(
        imageUrl = "",
        tag = "동참",
        tagColor = Color(0xFFFF7B69),
        name = "한걸음 닭꼬치",
        description = "헌신에 보답하는 감사의 식탁",
        warmthAmount = 10000,
        date = "2024.11.09"
    ),
    ProfileRestaurant(
        imageUrl = "",
        tag = "공유",
        tagColor = Color(0xFF5CA956),
        name = "한걸음 닭꼬치",
        description = "헌신에 보답하는 감사의 식탁",
        warmthAmount = 10000,
        date = "2024.11.09"
    ),
    ProfileRestaurant(
        imageUrl = "",
        tag = "동참",
        tagColor = Color(0xFFFF7B69),
        name = "한걸음 닭꼬치",
        description = "헌신에 보답하는 감사의 식탁",
        warmthAmount = 10000,
        date = "2024.11.09"
    ),
    ProfileRestaurant(
        imageUrl = "",
        tag = "공유",
        tagColor = Color(0xFF5CA956),
        name = "한걸음 닭꼬치",
        description = "헌신에 보답하는 감사의 식탁",
        warmthAmount = 10000,
        date = "2024.11.09"
    ),
    ProfileRestaurant(
        imageUrl = "",
        tag = "동참",
        tagColor = Color(0xFFFF7B69),
        name = "한걸음 닭꼬치",
        description = "헌신에 보답하는 감사의 식탁",
        warmthAmount = 10000,
        date = "2024.11.09"
    ),
    ProfileRestaurant(
        imageUrl = "",
        tag = "공유",
        tagColor = Color(0xFF5CA956),
        name = "한걸음 닭꼬치",
        description = "헌신에 보답하는 감사의 식탁",
        warmthAmount = 10000,
        date = "2024.11.09"
    ),
)


@Composable
internal fun ProfileRestaurantListScreen(
    appState: OnjungAppState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        TopBar(
            content = "함께한 식당",
            rightIcon = null,
            leftIconOnClick = {
                appState.navController.navigateUp()
            }
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(restaurants) {
                ProfileRestaurantItem(
                    imageUrl = it.imageUrl,
                    tag = it.tag,
                    tagColor = it.tagColor,
                    name = it.name,
                    description = it.description,
                    warmthAmount = it.warmthAmount,
                    date = it.date,
                    onClick = { }
                )
            }
        }
    }
}

@Composable
private fun ProfileRestaurantItem(
    imageUrl: String,
    tag: String,
    tagColor: Color,
    name: String,
    description: String,
    warmthAmount: Int,
    date: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Row(
            modifier = Modifier.padding(20.dp).height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(18.dp)),
                painter = painterResource(id = R.drawable.img_dummy),
                contentScale = ContentScale.Crop,
                contentDescription = "IMG_RESTAURANT",
            )

            Column {
                TagChip(
                    tag = tag,
                    color = tagColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = name,
                    style = OnjungTheme.typography.title.copy(
                        color = OnjungTheme.colors.text_1
                    )
                )
                Text(
                    text = description,
                    style = OnjungTheme.typography.body1.copy(
                        color = OnjungTheme.colors.text_1
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pig),
                        contentDescription = "IC_PIG",
                        tint = Color.Unspecified
                    )
                    Text(
                        text = formatCurrency(warmthAmount),
                        style = OnjungTheme.typography.body2.copy(
                            color = OnjungTheme.colors.text_3
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .width(2.dp)
                            .height(14.dp)
                            .background(
                                Color(0xFFD1D6DB)
                            )
                    )
                    Text(
                        text = date,
                        style = OnjungTheme.typography.body2.copy(
                            color = OnjungTheme.colors.text_3
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun TagChip(
    tag: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .background(
                color = color.copy(alpha = 0.1f),
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 4.dp),
            text = tag,
            style = OnjungTheme.typography.caption.copy(
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        )
    }
}