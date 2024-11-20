package com.daon.onjung.ui.auth.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SplashScreen(
    appState: OnjungAppState,
    viewModel: SplashViewModel
) {
    val effectFlow = viewModel.effect

    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            appState.showSnackBar("추후에 설정창으로 이동해 알람을 허용할 수 있습니다.")
        }
    }

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { event ->
            when (event) {
                is SplashContract.Effect.NavigateTo -> {
                    appState.navController.navigate(
                        route = event.destination,
                        navOptions = event.navOptions
                    )
                }

                is SplashContract.Effect.CheckNotificationPermission -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val isGranted = ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED

                        if (!isGranted) {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.white),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_app_logo),
                contentDescription = "App Logo",
                tint = OnjungTheme.colors.main_coral
            )
            Spacer(modifier = Modifier.height(44.dp))
            Text(
                text = "온정과 시작하는\n선행의 선순환",
                style = OnjungTheme.typography.h2.copy(
                    color = OnjungTheme.colors.main_coral,
                    lineHeight = 30.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}
