package com.daon.onjung.ui.mail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.network.model.OnjungMailStatus
import com.daon.onjung.network.model.OnjungType
import com.daon.onjung.network.model.response.OnjungMailResponse
import com.daon.onjung.network.model.response.OnjungMailStoreInfo
import com.daon.onjung.ui.component.ShopMailContainer
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

val mailList = listOf(
    OnjungMailResponse(
        storeInfo = OnjungMailStoreInfo(
            logoImgUrl = "https://daonjung.s3.ap-northeast-2.amazonaws.com/account_dummy3/logo_e2083312-ddba-4e23-9257-4f35d95f3c8c",
            title = "온기를 나누는 식당",
            name = "식당 이름"
        ),
        onjungType = OnjungType.SHARE,
        status = OnjungMailStatus.IN_PROGRESS,
        eventPeriod = "2024. 9. 16 ~ 9. 30",
        storeDeliveryDate = null,
        ticketIssueDate = null,
        reportDate = null
    ),
    OnjungMailResponse(
        storeInfo = OnjungMailStoreInfo(
            logoImgUrl = "https://daonjung.s3.ap-northeast-2.amazonaws.com/account_dummy3/logo_e2083312-ddba-4e23-9257-4f35d95f3c8c",
            title = "온기를 나누는 식당",
            name = "식당 이름"
        ),
        onjungType = OnjungType.SHARE,
        status = OnjungMailStatus.COMPLETED,
        eventPeriod = "2024. 9. 30",
        storeDeliveryDate = null,
        ticketIssueDate = null,
        reportDate = null
    ),
    OnjungMailResponse(
        storeInfo = OnjungMailStoreInfo(
            logoImgUrl = "https://daonjung.s3.ap-northeast-2.amazonaws.com/account_dummy3/logo_e2083312-ddba-4e23-9257-4f35d95f3c8c",
            title = "온기를 나누는 식당",
            name = "식당 이름"
        ),
        onjungType = OnjungType.SHARE,
        status = OnjungMailStatus.STORE_DELIVERY,
        eventPeriod = "2024. 9. 30",
        storeDeliveryDate = "2024. 10. 5",
        ticketIssueDate = null,
        reportDate = null
    ),
    OnjungMailResponse(
        storeInfo = OnjungMailStoreInfo(
            logoImgUrl = "https://daonjung.s3.ap-northeast-2.amazonaws.com/account_dummy3/logo_e2083312-ddba-4e23-9257-4f35d95f3c8c",
            title = "온기를 나누는 식당",
            name = "식당 이름"
        ),
        onjungType = OnjungType.SHARE,
        status = OnjungMailStatus.TICKET_ISSUE,
        eventPeriod = "2024. 9. 30",
        storeDeliveryDate = "2024. 10. 5",
        ticketIssueDate = "2024. 10. 7",
        reportDate = null
    ),
    OnjungMailResponse(
        storeInfo = OnjungMailStoreInfo(
            logoImgUrl = "https://daonjung.s3.ap-northeast-2.amazonaws.com/account_dummy3/logo_e2083312-ddba-4e23-9257-4f35d95f3c8c",
            title = "온기를 나누는 식당",
            name = "식당 이름"
        ),
        onjungType = OnjungType.SHARE,
        status = OnjungMailStatus.REPORT,
        eventPeriod = "2024. 9. 30",
        storeDeliveryDate = "2024. 10. 5",
        ticketIssueDate = "2024. 10. 7",
        reportDate = "2024. 10. 31"
    )
)

@Composable
internal fun MailScreen(
    appState: OnjungAppState,
    viewModel: MailViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is MailContract.Effect.NavigateTo -> {
                    appState.navController.navigate(effect.destination, effect.navOptions)
                }

                is MailContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisibleItemIndex == totalItems - 2 && !uiState.isMailListFetching) {
                    viewModel.processEvent(MailContract.Event.LoadMoreMailList)
                }
            }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        TopBar(
            "온기 우편함",
            rightIcon = null,
            leftIcon = null
        )
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                MailBanner()
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Text(
                    "${uiState.onjungCount}개의 식당과 온기를 나누었어요.",
                    style = OnjungTheme.typography.body2.copy(
                        color = OnjungTheme.colors.text_3,
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(uiState.mailList) { mail ->
                ShopMailContainer(
                    imageUrl = mail.storeInfo.logoImgUrl,
                    title = mail.storeInfo.title,
                    name = mail.storeInfo.name,
                    type = mail.onjungType,
                    status = mail.status,
                    eventPeriod = mail.eventPeriod,
                    storeDeliveryDate = mail.storeDeliveryDate,
                    ticketIssueDate = mail.ticketIssueDate,
                    reportDate = mail.reportDate
                ) { shopId ->
                    viewModel.processEvent(MailContract.Event.MailClicked(shopId))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
internal fun MailBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = OnjungTheme.colors.main_coral,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(
                RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp, end = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "온기를 나눈 식당의\n소식을 전해드려요.",
                style = OnjungTheme.typography.h2,
                color = OnjungTheme.colors.white
            )

            Icon(
                modifier = Modifier.offset(y = 3.dp),
                painter = painterResource(id = R.drawable.ic_mail_banner),
                contentDescription = "Mail Banner",
                tint = Color.Unspecified
            )
        }
    }
}
