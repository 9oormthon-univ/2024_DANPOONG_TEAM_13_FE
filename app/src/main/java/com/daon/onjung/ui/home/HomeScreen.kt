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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.R
import com.daon.onjung.Routes
import com.daon.onjung.rememberOnjungAppState
import com.daon.onjung.rememberOnjungBottomSheetState
import com.daon.onjung.ui.component.button.CircleButton
import com.daon.onjung.ui.home.component.HomeAppBar
import com.daon.onjung.ui.home.component.HomeBanner
import com.daon.onjung.ui.home.component.HomeDataBanner
import com.daon.onjung.ui.home.component.HomeShopCardLazyRow
import com.daon.onjung.ui.home.component.HomeTitleText
import com.daon.onjung.ui.home.component.SupportBannerRow
import com.daon.onjung.ui.theme.OnjungTheme

val iconList = listOf(
    IconData(
        icon = R.drawable.ic_nongsim_icon,
        contentDescription = "ic_nongsim_icon",
        color = Color(0xFF222222)
    ),
    IconData(
        icon = R.drawable.ic_cj_icon,
        contentDescription = "ic_cj_icon",
    ),
    IconData(
        icon = R.drawable.ic_goorm_icon,
        contentDescription = "ic_goorm_icon",
    ),
    IconData(
        icon = R.drawable.ic_kakao_icon,
        contentDescription = "ic_kakao_icon",
        color = Color(0xFFFAE100)
    ),
    IconData(
        icon = R.drawable.ic_coupang_icon,
        contentDescription = "ic_coupang_icon",
    )
)

val shopList = listOf(
    ShopData(
        shopId = 1,
        tag = "국가 유공자",
        tagColor = Color(0xFF81A5DA),
        title = "헌신에 보답하는\n감사의 식탁",
        likeCount = "1080",
        name = "한걸음 닭꼬치",
        region = "송파구"
    ),
    ShopData(
        shopId = 2,
        tag = "결식 아동",
        tagColor = Color(0xFF83CB82),
        title = "아이들에게\n전하는 희망의 밥상",
        likeCount = "250",
        name = "보람 돈까스",
        region = "용산구"
    ),
    ShopData(
        shopId = 3,
        tag = "결식 아동",
        tagColor = Color(0xFF83CB82),
        title = "아이들에게\n전하는 희망의 밥상",
        likeCount = "250",
        name = "보람 돈까스",
        region = "용산구"
    ),
)

@Composable
internal fun HomeScreen(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            appState.navigate(Routes.Home.CAMERA)
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
                    shopList
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
                    SupportBannerRow(
                        iconList
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                }
                Spacer(modifier = Modifier.height(32.dp))
                HomeDataBanner(
                    timeText = "2024.10.31 00시 00분",
                    warmthDeliveryCount = 1234567,
                    supporterCount = 123456,
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
            icon = R.drawable.ic_heart
        ) {
            if (context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                appState.navigate(Routes.Home.CAMERA)
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    OnjungTheme {
        val appState = rememberOnjungAppState()
        val bottomSheetState = rememberOnjungBottomSheetState()

        HomeScreen(
            appState = appState,
            bottomSheetState = bottomSheetState
        )
    }
}