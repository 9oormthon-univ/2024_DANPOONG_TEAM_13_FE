package com.daon.onjung.ui.home

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.daon.onjung.MainActivity
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.ui.component.ShopInfoContainer
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.component.YoutubeScreen
import com.daon.onjung.ui.component.button.CircleButton
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.theme.OnjungTheme
import com.daon.onjung.util.formatCurrencyInTenThousandUnit

data class ShopNews(
    val newsEntries: List<NewsEntry>
)

data class NewsEntry(
    val date: String,
    val deeds: List<Deed>
)

data class Deed(
    val description: String,
    val contributionAmount: Int
)

val news = ShopNews(
    newsEntries = listOf(
        NewsEntry(
            date = "2024년 5월",
            deeds = listOf(
                Deed(
                    description = "서초 국가유공자 복합센터",
                    contributionAmount = 80000
                ),
                Deed(
                    description = "보훈 어울림 행사장",
                    contributionAmount = 200000
                ),
                Deed(
                    description = "서울 국가유공자 기념 전시관",
                    contributionAmount = 3200000
                )
            )
        ),
        NewsEntry(
            date = "2024년 5월",
            deeds = listOf(
                Deed(
                    description = "서초 국가유공자 복합센터",
                    contributionAmount = 1000000
                ),
                Deed(
                    description = "보훈 어울림 행사장",
                    contributionAmount = 200000
                )
            )
        ),
        NewsEntry(
            date = "2024년 5월",
            deeds = listOf(
                Deed(
                    description = "한줄",
                    contributionAmount = 10000000
                )
            )
        )
    )
)

@Composable
internal fun ShopDetailScreen(
    appState: OnjungAppState
) {
    var isExpanded by remember { mutableStateOf(true) }

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
                    ShopDetailHeader(
                        title = "헌신에 보답하는\n감사의 식탁",
                        tag = "국가 유공자",
                        tagColor = Color(0xFF71AAFF)
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
                    name = "한걸음 닭꼬치",
                    imageUrl = "",
                    tag = "일식",
                    address = "송파구 오금로 533 1층 (거여동)",
                    totalFundsRaised = 121800,
                    updatePeriod = "D-13",
                    totalVisitedUsers = 246,
                    mealTicketUsers = 14,
                    mealTicketPaymentAmount = 114000,
                    warmthContributionAmount = 7800,
                    isExpanded = isExpanded,
                ) {
                    isExpanded = it
                }

                Spacer(modifier = Modifier.height(8.dp))

                ShopDetailVideoSection(
                    introduce = "\"더 좋은 가격과 퀄리티를 주도록 노력하겠습니다.\"",
                    youtubeVideoId = "sHmo2uGcP2Q"
                )

                Spacer(modifier = Modifier.height(8.dp))

                ShopDetailNewsSection(newsEntries = news.newsEntries)

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

                }

                FilledWidthButton(
                    text = "동참하기",
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    )
                ) {

                }
            }
        }
    }
}

@Composable
private fun ShopDetailHeader(
    title: String,
    tag: String,
    tagColor: Color
) {
    Box(
        modifier = Modifier.aspectRatio(1.17f),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            modifier = Modifier.aspectRatio(1.17f),
            painter = painterResource(id = R.drawable.img_dummy),
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

            YoutubeScreen(
                youtubeVideoId = youtubeVideoId,
                lifecycleOwner = context as MainActivity
            )
        }
    }
}

@Composable
private fun ShopDetailNewsSection(
    newsEntries: List<NewsEntry>,
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
            )
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

            newsEntries.forEachIndexed { index, newsEntry ->
                ShopNewsItem(
                    newsEntry,
                    isFirst = index == 0,
                    isLast = index == newsEntries.size - 1
                )
            }
        }
    }
}

@Composable
private fun ShopNewsItem(
    newsEntry: NewsEntry,
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
                text = newsEntry.date,
                style = OnjungTheme.typography.caption,
                color = OnjungTheme.colors.text_3
            )

            Spacer(modifier = Modifier.height(8.dp))

            newsEntry.deeds.forEach { deed ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = deed.description,
                        style = OnjungTheme.typography.body2,
                        color = OnjungTheme.colors.text_1
                    )

                    Text(
                        text = formatCurrencyInTenThousandUnit(deed.contributionAmount),
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