package com.daon.onjung.ui.donation.donation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.Routes
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.donation.component.DonationSelectPrice
import com.daon.onjung.ui.donation.component.DonationStore
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest


@Composable
fun DonationScreen(
    appState: OnjungAppState,
    shopId: Int,
    viewModel: DonationViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    LaunchedEffect(Unit) {
        viewModel.getStoreDetailInfo(shopId)

        effectFlow.collectLatest { effect ->
            when (effect) {
                is DonationContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is DonationContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.gray_2),
    ) {
        TopBar(
            "동참하기",
            rightIcon = null
        )
        DonationStore(
            shopInfo = uiState.storeInfo
        )
        Spacer(modifier = Modifier.height(8.dp))
        DonationSelectPrice(
            price = uiState.amount
        ) { price ->
            viewModel.processEvent(DonationContract.Event.AmountChangedClicked(price))
        }
        Spacer(modifier = Modifier.weight(1f))
        FilledWidthButton(
            "카카오페이로 결제하기",
            modifier = Modifier
                .padding(horizontal = 20.dp),
            fontSize = 18
        ) {
            appState.navigate(Routes.Donation.KAKAOPAY)
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
fun DonationScreenPreview(
) {
    val appState = rememberOnjungAppState()
    val viewModel: DonationViewModel = hiltViewModel()
    val shopId = 1
    OnjungTheme {
        DonationScreen(
            appState = appState,
            shopId = shopId,
            viewModel = viewModel
        )
    }
}