package com.daon.onjung.ui.home.shopdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.MainActivity
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.Routes
import com.daon.onjung.network.model.StoreTag
import com.daon.onjung.network.model.response.StoreHistory
import com.daon.onjung.ui.component.ShopInfoContainer
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.component.YoutubeScreen
import com.daon.onjung.ui.component.button.CircleButton
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.home.component.OnjungSuccessDialog
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun ShopDetailScreen(
    appState: OnjungAppState,
    shopId: Int,
    viewModel: ShopDetailViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        viewModel.getStoreDetailInfo(shopId)

        effectFlow.collectLatest { effect ->
            when (effect) {
                is ShopDetailContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is ShopDetailContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }

    if (uiState.isOnjungShareDialogVisible) {
        OnjungSuccessDialog(
            title = "온기를 나눠주셔서 감사해요",
            description = "온정이 대신 100원 기부했어요!"
        ) {
            viewModel.processEvent(ShopDetailContract.Event.OnjungShareDialogDismissed)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        TopBar(
            "식당 상세",
            leftIconOnClick = {
                appState.navController.navigateUp()
            },
            rightIconOnClick = {
                appState.navController.navigateUp()
            }
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    val (tag, tagColor) = when (uiState.storeInfo.tags.first()) {
                        StoreTag.DISABLED_GROUP -> {
                            "장애우" to Color(0xFF81A5DA).copy(alpha = 0.8f)
                        }
                        StoreTag.GOOD_PRICE -> {
                            "착한 가격" to Color(0xFFF5AB67).copy(alpha = 0.8f)
                        }
                        StoreTag.UNDERFED_CHILD -> {
                            "결식아동" to Color(0xFF83CB82).copy(alpha = 0.8f)
                        }
                    }

                    ShopDetailHeader(
                        title = uiState.storeInfo.title,
                        tag = tag,
                        tagColor = tagColor,
                        bannerImgUrl = uiState.storeInfo.bannerImgUrl
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .background(
                                color = OnjungTheme.colors.white,
                                shape = RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp
                                )
                            )
                    )
                }
                ShopInfoContainer(
                    name = uiState.storeInfo.name,
                    imageUrl = uiState.storeInfo.logoImgUrl,
                    category = uiState.storeInfo.category,
                    address = uiState.storeInfo.address,
                    totalFundsRaised = uiState.eventInfo.totalAmount,
                    restOfDate = uiState.eventInfo.restOfDate,
                    totalVisitedUsers = uiState.onjungInfo.totalDonatorCount,
                    totalDonationAmount = uiState.onjungInfo.totalDonationAmount,
                    mealTicketPaymentAmount = uiState.onjungInfo.totalReceiptAmount,
                    warmthContributionAmount = uiState.onjungInfo.totalSharedAmount,
                    isExpanded = uiState.isExpanded,
                ) {
                    viewModel.processEvent(ShopDetailContract.Event.ToggleExpand(it))
                }

                Spacer(modifier = Modifier.height(8.dp))

                ShopDetailVideoSection(
                    introduce = "\"더 좋은 가격과 퀄리티를 주도록 노력하겠습니다.\"",
                    youtubeVideoId = uiState.storeInfo.youtubeUrl
                )
                Spacer(modifier = Modifier.height(8.dp))

                ShopDetailHistorySection(storeHistories = uiState.storeHistories)

                Spacer(modifier = Modifier.height(215.dp))

            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                CircleButton(
                    "온기 공유",
                    modifier = Modifier.padding(
                        end = 20.dp,
                    ),
                    icon = R.drawable.ic_heart
                ) {
                    viewModel.processEvent(ShopDetailContract.Event.OnjungShareClicked(shopId))
                }

                FilledWidthButton(
                    text = "동참하기",
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    )
                ) {
                    appState.navigate("${Routes.Donation.ROUTE}?shopId=$shopId")
                }
            }
        }
    }
}

@Composable
private fun ShopDetailHeader(
    title: String,
    tag: String,
    tagColor: Color,
    bannerImgUrl: String
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.aspectRatio(1.17f),
        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            modifier = Modifier.aspectRatio(1.17f),
            model = ImageRequest.Builder(context)
                .data(bannerImgUrl)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "IMG_SHOP",
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.17f)
                .background(
                    color = OnjungTheme.colors.text_1.copy(
                        alpha = 0.5f
                    )
                )
        )

        Column(
            modifier = Modifier.padding(
                start = 20.dp,
                bottom = 48.dp
            )
        ) {
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
            )
        }
    }
}

@Composable
private fun ShopDetailVideoSection(
    youtubeVideoId: String,
    introduce: String
) {
    val context = LocalContext.current
    val lifecycleOwner = context as MainActivity

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = OnjungTheme.colors.white,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
    ) {
        Column {
            Text(
                "가게를 소개해요.",
                style = OnjungTheme.typography.h2.copy(
                    color = OnjungTheme.colors.text_1
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = introduce,
                style = OnjungTheme.typography.body2.copy(
                    color = OnjungTheme.colors.text_3
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (youtubeVideoId.isNotEmpty()) {
                YoutubeScreen(
                    youtubeVideoId = youtubeVideoId,
                    lifecycleOwner = context
                )
            }
        }
    }
}

@Composable
private fun ShopDetailHistorySection(
    storeHistories: List<StoreHistory>,
) {
    Box(
        modifier = Modifier.background(
            color = OnjungTheme.colors.white,
            shape = RoundedCornerShape(20.dp)
        )
    ) {
        Column(
            modifier = Modifier.padding(
                top = 20.dp,
                bottom = 20.dp,
                start = 20.dp,
                end = 24.dp
            ).fillMaxWidth()
        ) {
            Text(
                "선행 소식",
                style = OnjungTheme.typography.h2,
                color = OnjungTheme.colors.text_1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "가게가 지출한 선행 내역이예요.",
                style = OnjungTheme.typography.body2,
                color = OnjungTheme.colors.text_2
            )

            Spacer(modifier = Modifier.height(40.dp))

            if (storeHistories.isNotEmpty()) {
                storeHistories.forEachIndexed { index, storeHistory ->
                    ShopHistoryItem(
                        storeHistory,
                        isFirst = index == 0,
                        isLast = index == storeHistories.size - 1
                    )
                }
            } else {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 20.dp),
                    text = "아직 선행 내역이 없어요.",
                    style = OnjungTheme.typography.body2,
                    color = OnjungTheme.colors.text_2
                )
            }
        }
    }
}

@Composable
private fun ShopHistoryItem(
    history: StoreHistory,
    isFirst: Boolean,
    isLast: Boolean
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(28.dp)
                .fillMaxHeight()
        ) {
            Spacer(
                modifier = Modifier
                    .size(2.dp)
                    .background(
                        color = if (isFirst) {
                            Color.Transparent
                        } else {
                            OnjungTheme.colors.main_coral
                        }
                    )
            )
            Spacer(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color = OnjungTheme.colors.main_coral,
                        shape = CircleShape
                    )
            )
            Spacer(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(
                        if (isLast) {
                            Color.Transparent
                        } else {
                            OnjungTheme.colors.main_coral
                        }
                    )
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = history.date,
                style = OnjungTheme.typography.caption,
                color = OnjungTheme.colors.text_3
            )

            Spacer(modifier = Modifier.height(8.dp))

            history.info.forEach { deed ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = deed.content,
                        style = OnjungTheme.typography.body2,
                        color = OnjungTheme.colors.text_1
                    )

                    Text(
                        text = deed.amount,
                        style = OnjungTheme.typography.body2,
                        color = OnjungTheme.colors.text_2
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
