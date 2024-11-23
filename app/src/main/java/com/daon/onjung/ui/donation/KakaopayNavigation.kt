package com.daon.onjung.ui.donation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes
import com.daon.onjung.ui.donation.donation.DonationScreen
import com.daon.onjung.ui.donation.donation.DonationViewModel

fun NavGraphBuilder.donationGraph(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
    composable(
        route = "${Routes.Donation.ROUTE}?shopId={shopId}",
        arguments = listOf(
            navArgument("shopId") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { entry ->
        val viewModel: DonationViewModel = hiltViewModel()
        val shopIdString = entry.arguments?.getString("shopId") ?: "0"
        val shopId = shopIdString.toInt()
        DonationScreen(
            appState = appState,
            shopId = shopId,
            viewModel = viewModel
        )
    }

    composable(
        route = Routes.Donation.KAKAOPAY
    ) {
        KakaopayScreen(
            appState = appState
        )
    }

    composable(
        route = Routes.Donation.KAKAOPAYPAYMENT
    ) {
        KakaopayPaymentScreen(
            appState = appState
        )
    }

    composable(
        route = Routes.Donation.KAKAOPAYRESULT
    ) {
        KakaopayResultScreen(
            appState = appState
        )
    }

    composable(
        route = Routes.Donation.DONDATIONRESULT
    ) {
        DonationResultScreen(
            appState = appState
        )
    }
}