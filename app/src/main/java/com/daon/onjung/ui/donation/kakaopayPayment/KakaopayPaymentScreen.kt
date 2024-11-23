package com.daon.onjung.ui.donation.kakaopayPayment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.navOptions
import com.daon.onjung.OnjungAppState
import com.daon.onjung.Routes
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.donation.component.KakaoPrivateCheck
import com.daon.onjung.ui.donation.component.KakaopayButton
import com.daon.onjung.ui.donation.component.KakaopayCardview
import com.daon.onjung.ui.donation.component.KakaopayPayPoint
import com.daon.onjung.ui.donation.component.KakaopayTopBar
import com.daon.onjung.ui.theme.OnjungTheme
import com.daon.onjung.util.formatCurrency
import kotlinx.coroutines.flow.collectLatest

@Composable
fun KakaopayPaymentScreen(
    appState: OnjungAppState,
    shopId: Int,
    amount: Int,
    viewModel: KakaopayPaymentViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        viewModel.getStoreDetailInfo(shopId)

        effectFlow.collectLatest { effect ->
            when (effect) {
                is KakaopayPaymentContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is KakaopayPaymentContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }

    var isChecked by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.gray_2),
    ){
        Spacer(modifier = Modifier.height(30.dp))
        KakaopayTopBar(
            modifier = Modifier.padding(
                end = 4.dp
            )
        ) {
            appState.navigate("${Routes.Donation.ROUTE}?shopId=$shopId", navOptions{
                popUpTo(Routes.Donation.ROUTE) {
                    inclusive = true
                }
            })
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            "${uiState.storeInfo.name} - ${uiState.storeInfo.title}",
            style = OnjungTheme.typography.body2.copy(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 18.dp)
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            formatCurrency(amount),
            style = OnjungTheme.typography.h1.copy(
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.padding(start = 18.dp)
        )
        Spacer(modifier = Modifier.height(7.dp))
        KakaopayPayPoint(
            modifier = Modifier.padding(horizontal = 18.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        KakaopayCardview(
            money = 1000000,
            modifier = Modifier.padding(horizontal = 38.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
        KakaoPrivateCheck(
            idChecked = isChecked,
            onCheckBtnClick = { isChecked = !isChecked },
            modifier = Modifier.padding(horizontal = 25.dp)
        )
        Spacer(modifier = Modifier.height(11.13.dp))
        KakaopayButton(
            "동의하고 ${formatCurrency(amount)} 결제하기",
            modifier = Modifier
                .padding(horizontal = 14.dp)
        ) {
            appState.navigate("${Routes.Donation.KAKAOPAYRESULT}?shopId=$shopId&amount=$amount")
        }
    }
}

@Preview
@Composable
fun KakaopayPaymentScreenPreview() {
    OnjungTheme {
        val viewModel: KakaopayPaymentViewModel = hiltViewModel()
        val appState = rememberOnjungAppState()
        val shopId = 1
        val amount = 10000
        KakaopayPaymentScreen(
            appState = appState,
            shopId = shopId,
            amount = amount,
            viewModel = viewModel
        )
    }
}