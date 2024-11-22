package com.daon.onjung.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.network.model.OnjungType
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.theme.OnjungTheme
import com.daon.onjung.util.formatCurrency
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun ProfileRestaurantListScreen(
    appState: OnjungAppState,
    viewModel: ProfileRestaurantListViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is ProfileRestaurantListContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is ProfileRestaurantListContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }

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
            items(uiState.restaurantList) { restaurant ->
                ProfileRestaurantItem(
                    imageUrl = restaurant.logoImgUrl,
                    shopId = restaurant.storeId,
                    type = restaurant.onjungType,
                    name = restaurant.storeName,
                    description = restaurant.storeTitle,
                    warmthAmount = restaurant.amount,
                    date = restaurant.date,
                    onClick = { shopId ->
                        viewModel.processEvent(ProfileRestaurantListContract.Event.RestaurantClicked(shopId))
                    }
                )
            }
        }
    }
}

@Composable
private fun ProfileRestaurantItem(
    imageUrl: String,
    shopId: Int,
    type: OnjungType,
    name: String,
    description: String,
    warmthAmount: Int,
    date: String,
    onClick: (Int) -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.clickable {
            onClick(shopId)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .size(104.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        color = OnjungTheme.colors.main_coral,
                        shape = RoundedCornerShape(18.dp)
                    ),
                model = ImageRequest.Builder(context).data(imageUrl).build(),
                contentScale = ContentScale.Crop,
                contentDescription = "IMG_SHOP",
            )

            Column {
                val (tag, tagColor) = when (type) {
                    OnjungType.DONATION -> "동참" to Color(0xFFFF7B69)
                    OnjungType.SHARE -> "공유" to Color(0xFF5CA956)
                    OnjungType.RECEIPT -> "방문" to Color(0xFF698AFF)
                }

                TagChip(
                    tag = tag,
                    color = tagColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = name,
                    style = OnjungTheme.typography.title.copy(
                        color = OnjungTheme.colors.text_1
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = description,
                    style = OnjungTheme.typography.body1.copy(
                        color = OnjungTheme.colors.text_1
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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