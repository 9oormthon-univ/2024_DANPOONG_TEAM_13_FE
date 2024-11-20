package com.daon.onjung.ui.donation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.Routes

fun NavGraphBuilder.donationGraph(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
    composable(
        route = Routes.Donation.ROUTE
    ) {
        DonationScreen(
            appState = appState
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