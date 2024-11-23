package com.daon.onjung.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.R
import com.daon.onjung.Routes
import com.daon.onjung.ui.component.button.CircleButton
import com.daon.onjung.ui.home.component.HomeAppBar
import com.daon.onjung.ui.home.component.HomeBanner
import com.daon.onjung.ui.home.component.HomeDataBanner
import com.daon.onjung.ui.home.component.HomeShopCardLazyRow
import com.daon.onjung.ui.home.component.HomeTitleText
import com.daon.onjung.ui.home.component.SupportBannerRow
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun HomeScreen(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState,
    viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is HomeContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is HomeContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            appState.navigate(Routes.Home.OCR_CAMERA)
        } else {
            Toast.makeText(context, "카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            HomeAppBar {
                appState.navigate(Routes.Setting.ROUTE)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                HomeBanner(
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                HomeTitleText(
                    "선행의 물결 함께하기",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                HomeShopCardLazyRow(
                    shopList = uiState.storeList,
                    isLastPage = uiState.isStoreListLastPage,
                    onLoadMore = {
                        viewModel.processEvent(HomeContract.Event.LoadMoreStoreList)
                    },
                    navigateToShopDetail = { id ->
                        appState.navigate("${Routes.Home.SHOP_DETAIL}?shopId=$id")
                    }
                )
                Column(
                    modifier = Modifier
                        .background(color = OnjungTheme.colors.gray_3)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    HomeTitleText(
                        "온기를 전한 기업들",
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    SupportBannerRow(uiState.supportCompanies)
                    Spacer(modifier = Modifier.height(26.dp))
                }
                Spacer(modifier = Modifier.height(32.dp))
                HomeDataBanner(
                    timeText = uiState.onjungSummary.dateTime,
                    warmthDeliveryCount = uiState.onjungSummary.totalDonationCount,
                    supporterCount = uiState.onjungSummary.totalDonatorCount,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        CircleButton(
            modifier = Modifier
                .align(
                    alignment = Alignment.BottomEnd
                )
                .padding(
                    end = 20.dp,
                    bottom = 25.dp
                ),
            text = "영수증 인증",
            icon = R.drawable.ic_invoice,
        ) {
            if (context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                appState.navigate(Routes.Home.OCR_CAMERA)
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}