package com.daon.onjung.ui.profile

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.R
import com.daon.onjung.ui.component.MealTicket
import com.daon.onjung.ui.component.TicketState
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.profile.component.MealTicketBottomSheet
import com.daon.onjung.ui.theme.OnjungTheme

data class MealTicketEntity(
    val name: String,
    val imageUrl: String,
    val tag: String,
    val address: String,
    val state: TicketState,
    val date: String
)

val tickets = listOf(
    MealTicketEntity(
        name = "한걸음 닭꼬치",
        imageUrl = "https://via.placeholder.com/150",
        tag = "일식",
        address = "송파구 오금로 533 1층 (거여동)",
        state = TicketState.AVAILABLE,
        date = "2024. 12. 31",
    ),

    MealTicketEntity(
        name = "한걸음 닭꼬치",
        imageUrl = "https://via.placeholder.com/150",
        tag = "일식",
        address = "송파구 오금로 533 1층 (거여동)",
        state = TicketState.USED,
        date = "2024. 12. 31",
    ),

    MealTicketEntity(
        name = "한걸음 닭꼬치",
        imageUrl = "https://via.placeholder.com/150",
        tag = "일식",
        address = "송파구 오금로 533 1층 (거여동)",
        state = TicketState.EXPIRED,
        date = "2024. 12. 31",
    )
)

@Composable
internal fun ProfileTicketListScreen(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
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
                        "${tickets.size}개의 식권을\n보유하고 있어요.",
                        style = OnjungTheme.typography.h1.copy(
                            fontWeight = FontWeight.Bold,
                            color = OnjungTheme.colors.text_1,
                        ),
                    )

                    Image(
                        modifier = Modifier.width(128.dp).height(87.dp),
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

            items(tickets) { ticket ->
                MealTicket(
                    ticket.name,
                    ticket.imageUrl,
                    ticket.tag,
                    ticket.address,
                    ticket.state,
                    ticket.date
                ) {
                    bottomSheetState.showBottomSheet {
                        MealTicketBottomSheet(
                            name = ticket.name,
                            address = ticket.address,
                            expirationDate = ticket.date
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

