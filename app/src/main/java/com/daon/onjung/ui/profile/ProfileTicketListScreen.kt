package com.daon.onjung.ui.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.R
import com.daon.onjung.ui.component.MealTicket
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.profile.component.MealTicketBottomSheet
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ProfileTicketListScreen(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState,
    viewModel: ProfileTicketListViewModel,
    ticketCount: Int
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is ProfileTicketListContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is ProfileTicketListContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }

                is ProfileTicketListContract.Effect.ShowTicketBottomSheet -> {
                    bottomSheetState.showBottomSheet(
                        content = {
                            MealTicketBottomSheet(
                                qrCodeImgUrl = effect.qrCodeImgUrl,
                                name = effect.name,
                                address = effect.address,
                                expirationDate = effect.expirationDate
                            )
                        }
                    )
                }
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisibleItemIndex == totalItems - 1 && !uiState.isTicketListLastPage) {
                    viewModel.processEvent(ProfileTicketListContract.Event.LoadMoreTicketList)
                }
        }
    }

    BackHandler {
        if (bottomSheetState.bottomSheetState.isVisible) {
            bottomSheetState.hideBottomSheet()
        } else {
            appState.navController.navigateUp()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFEEDF),
                        Color.Transparent,
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        TopBar(
            "나의 식권",
            rightIcon = null,
            leftIconOnClick = {
                appState.navController.navigateUp()
            }
        )
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {
            item {
                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "${ticketCount}개의 식권을\n보유하고 있어요.",
                        style = OnjungTheme.typography.h1.copy(
                            fontWeight = FontWeight.Bold,
                            color = OnjungTheme.colors.text_1,
                        ),
                    )

                    Image(
                        modifier = Modifier
                            .width(128.dp)
                            .height(87.dp),
                        painter = painterResource(id = R.mipmap.img_meal_ticket),
                        contentDescription = "IMG_MEAL_TICKET",
                    )
                }

                Text(
                    "식당의 1만원 금액권으로 제공돼요.",
                    style = OnjungTheme.typography.caption.copy(
                        color = OnjungTheme.colors.text_3,
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            items(uiState.ticketList) { ticket ->
                MealTicket(
                    ticket.storeInfo.name,
                    ticket.storeInfo.logoImgUrl,
                    ticket.storeInfo.category,
                    ticket.storeInfo.address,
                    ticket.expirationDate,
                    ticket.isValidate
                ) {
                    viewModel.processEvent(ProfileTicketListContract.Event.MealTicketClicked(ticket.id))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

