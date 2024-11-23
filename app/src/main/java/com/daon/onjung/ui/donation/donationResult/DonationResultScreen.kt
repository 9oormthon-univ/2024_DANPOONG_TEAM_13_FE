package com.daon.onjung.ui.donation.donationResult

import android.content.Context
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.navOptions
import com.daon.onjung.OnjungAppState
import com.daon.onjung.Routes
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.donation.component.DonationResultCard
import com.daon.onjung.ui.donation.component.KakaoShare
import com.daon.onjung.ui.theme.OnjungTheme
import com.daon.onjung.util.formatCurrency
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DonationResultScreen(
    appState: OnjungAppState,
    shopId: Int,
    amount: Int,
    issueDate: String,
    viewModel : DonationResultViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getStoreDetailInfo(shopId)

        effectFlow.collectLatest { effect ->
            when (effect) {
                is DonationResultContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is DonationResultContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(77.dp))
        Text(
            "${formatCurrency(amount)} 후원 완료",
            style = OnjungTheme.typography.h1.copy(
                color = OnjungTheme.colors.text_1,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "따뜻한 세상을 만들어 주셔서 감사합니다.",
            style = OnjungTheme.typography.body1.copy(
                color = OnjungTheme.colors.text_3,
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = Modifier.height(37.dp))
        DonationResultCard(
            date = issueDate,
            imageUrl = uiState.storeInfo.logoImgUrl,
            tag = uiState.storeInfo.tags.first(),
            name = uiState.storeInfo.name,
            modifier = Modifier.padding(horizontal = 50.dp)
        )
        Spacer(modifier = Modifier.height(27.dp))
        KakaoShare(
            onClick = {
                shareNoteWithKakaoLink(
                    context,
                    uiState.storeInfo.title,
                    "선한 영향력, 지금 이 식당과 함께 시작해보세요!",
                    uiState.storeInfo.bannerImgUrl,
                    appState::showSnackBar
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        FilledWidthButton(
            "확인",
            modifier = Modifier
                .padding(horizontal = 20.dp),
        ) {
            appState.navigate("${Routes.Home.SHOP_DETAIL}?shopId=$shopId", navOptions{
                popUpTo(Routes.Home.SHOP_DETAIL) {
                    inclusive = true
                }
            })
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
fun DonationResultScreenPreview() {
    val viewModel : DonationResultViewModel = hiltViewModel()
    val appState = rememberOnjungAppState()
    OnjungTheme {
        DonationResultScreen(
            appState = appState,
            shopId = 1,
            amount = 10000,
            issueDate = "2024. 10. 31",
            viewModel = viewModel
        )
    }
}

private fun shareNoteWithKakaoLink(
    context: Context,
    title: String,
    description: String,
    imageUrl: String,
    showErrorMessage: (String) -> Unit
) {
    if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
        ShareClient.instance.shareCustom(
            context,
            114585L,
            templateArgs = mapOf(
                "title" to title,
                "description" to description,
                "image" to imageUrl
            )
        ) { sharingResult, error ->
            if (error != null) {
                showErrorMessage("카카오톡 공유 실패")
            } else {
                context.startActivity(sharingResult?.intent)
            }
        }
    } else {
        val shareUrl = WebSharerClient.instance.makeCustomUrl(
            114585L,
            templateArgs = mapOf(
                "title" to title,
                "description" to description,
                "image" to imageUrl
            )
        )

        try {
            KakaoCustomTabsClient.openWithDefault(context, shareUrl)
        } catch (e: Exception) {
            showErrorMessage("카카오톡 공유 실패")
        }

        try {
            KakaoCustomTabsClient.openWithDefault(context, shareUrl)
        } catch (e: Exception) {
            showErrorMessage("카카오톡 공유 실패")
        }
    }
}