package com.daon.onjung.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.MainActivity
import com.daon.onjung.R
import com.daon.onjung.ui.component.ShopInfoContainer
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.component.YoutubeScreen
import com.daon.onjung.ui.component.button.CircleButton
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.theme.OnjungTheme

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

@Preview(showBackground = true)
@Composable
internal fun ShopDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        TopBar(
            "식당 상세",
            leftIconOnClick = {}
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ShopDetailHeader(
                    title = "헌신에 보답하는\n감사의 식탁",
                    tag = "국가 유공자",
                    tagColor = Color(0xFF71AAFF)
                )

                ShopInfoContainer(
                    modifier = Modifier.offset(
                        y = -24.dp
                    ),
                    name = "한걸음 닭꼬치",
                    imageUrl = "",
                    tag = "일식",
                    address = "송파구 오금로 533 1층 (거여동)",
                    totalFundsRaised = 121800,
                    updatePeriod = "D-13",
                    totalVisitedUsers = 246,
                    mealTicketUsers = 14,
                    mealTicketPaymentAmount = 114000,
                    warmthContributionAmount = 7800
                ) {

                }

                Spacer(modifier = Modifier.height(8.dp))

                ShopDetailVideoSection(
                    introduce = "\"더 좋은 가격과 퀄리티를 주도록 노력하겠습니다.\"",
                    youtubeVideoId = "NfmZC0SfB-s"
                )

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

) {

}