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
import com.daon.onjung.ui.donation.kakaopayPayment.KakaopayPaymentScreen
import com.daon.onjung.ui.donation.kakaopayPayment.KakaopayPaymentViewModel
import com.daon.onjung.ui.donation.kakaopayResult.KakaopayResultScreen
import com.daon.onjung.ui.donation.kakaopayResult.KakaopayResultViewModel

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
        route = "${Routes.Donation.KAKAOPAY}?shopId={shopId}&amount={amount}",
        arguments = listOf(
            navArgument("shopId") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("amount") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { entry ->
        val shopIdString = entry.arguments?.getString("shopId") ?: "0"
        val shopId = shopIdString.toInt()
        val amountString = entry.arguments?.getString("amount") ?: "0"
        val amount = amountString.toInt()
        KakaopayScreen(
            appState = appState,
            shopId = shopId,
            amount = amount
        )
    }

    composable(
        route = "${Routes.Donation.KAKAOPAYPAYMENT}?shopId={shopId}&amount={amount}",
        arguments = listOf(
            navArgument("shopId") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("amount") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { entry ->
        val viewModel: KakaopayPaymentViewModel = hiltViewModel()
        val shopIdString = entry.arguments?.getString("shopId") ?: "0"
        val shopId = shopIdString.toInt()
        val amountString = entry.arguments?.getString("amount") ?: "0"
        val amount = amountString.toInt()
        KakaopayPaymentScreen(
            appState = appState,
            shopId = shopId,
            amount = amount,
            viewModel = viewModel
        )
    }

    composable(
        route = "${Routes.Donation.KAKAOPAYRESULT}?shopId={shopId}&amount={amount}",
        arguments = listOf(
            navArgument("shopId") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("amount") {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) { entry ->
        val viewModel: KakaopayResultViewModel = hiltViewModel()
        val shopIdString = entry.arguments?.getString("shopId") ?: "0"
        val shopId = shopIdString.toInt()
        val amountString = entry.arguments?.getString("amount") ?: "0"
        val amount = amountString.toInt()
        KakaopayResultScreen(
            appState = appState,
            shopId = shopId,
            amount = amount,
            viewModel = viewModel
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