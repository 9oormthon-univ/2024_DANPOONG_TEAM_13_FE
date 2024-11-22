package com.daon.onjung.ui.setting

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.Routes
import com.daon.onjung.ui.component.NoRippleInteractionSource
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.setting.component.Profile
import com.daon.onjung.ui.setting.component.SettingDialog
import com.daon.onjung.ui.setting.component.SettingTitleText
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest


@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun SettingScreen(
    appState: OnjungAppState,
    viewModel: SettingViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is SettingContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }
                is SettingContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(
            "설정",
            rightIcon = null,
            leftIconOnClick = {
                appState.navigate(Routes.Home.ROUTE)
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Profile(
            name = uiState.userName,
            imageUrl = uiState.profileImgUrl,
        )
        Spacer(modifier = Modifier.height(12.dp))
        SettingTitleText(
            title = "알림 설정",
            color = OnjungTheme.colors.text_3,
            modifier = Modifier.padding(start = 21.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        NotificationSettingSection(
            modifier = Modifier.padding(horizontal = 20.dp),
            isChecked = uiState.notificationAllowed
        ) {
            if (!uiState.notificationAllowed) {
                val isPermissionGranted = isNotificationPermissionGranted(context)

                if (!isPermissionGranted) {
                    Intent().apply {
                        // 알림 권한 설정 or 권한 설정 페이지로 이동
                        action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                        context.startActivity(this)
                    }
                    return@NotificationSettingSection
                }
            }
            viewModel.processEvent(SettingContract.Event.ToggleNotification)
        }
        Spacer(modifier = Modifier.height(33.dp))
        SettingTitleText(
            title = "계정 정보",
            color = OnjungTheme.colors.text_2,
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        AccountSettingSection(
            modifier = Modifier.padding(horizontal = 20.dp),
            onLogOut = { viewModel.processEvent(SettingContract.Event.LogoutDialogOpen) },
            onWithdrawal = { viewModel.processEvent(SettingContract.Event.DeleteAccountDialogOpen) }
        )
        if (uiState.isLogoutDialogVisible) {
            SettingDialog(
                text = "로그아웃 하시겠어요?",
                onConfirm = {
                    viewModel.processEvent(SettingContract.Event.Logout)
                    viewModel.processEvent(SettingContract.Event.LogoutDialogDismissed)
                },
                onDismissRequest = { viewModel.processEvent(SettingContract.Event.LogoutDialogDismissed) }
            )
        }
        if (uiState.isDeleteAccountDialogVisible) {
            SettingDialog(
                text = "정말로 탈퇴하시겠어요?",
                onConfirm = {
                    viewModel.processEvent(SettingContract.Event.DeleteAccount)
                    viewModel.processEvent(SettingContract.Event.DeleteAccountDialogDismissed)
                },
                onDismissRequest = { viewModel.processEvent(SettingContract.Event.DeleteAccountDialogDismissed) }
            )
        }
    }
}

@Composable
private fun NotificationSettingSection(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onToggle: () -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)) // 모서리를 둥글게 처리
            .background(OnjungTheme.colors.gray_3) // 배경색 설정
            .padding(start = 20.dp, end = 18.dp, top = 12.dp, bottom = 12.dp) // 내부 패딩 설정
    ) {
        Surface(
            color = OnjungTheme.colors.gray_3,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                     "PUSH 알림",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = OnjungTheme.colors.text_2
                    )
                )
                Switch(
                    checked = isChecked,
                    onCheckedChange = { onToggle() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = OnjungTheme.colors.white,
                        checkedTrackColor = OnjungTheme.colors.main_coral,
                        uncheckedThumbColor = OnjungTheme.colors.gray_1,
                        uncheckedTrackColor = OnjungTheme.colors.gray_1.copy(alpha = 0.5f),
                        uncheckedBorderColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Composable
private fun AccountSettingSection(
    modifier: Modifier = Modifier,
    onLogOut: () -> Unit,
    onWithdrawal: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)) // 모서리를 둥글게 처리
            .background(OnjungTheme.colors.gray_3) // 배경색 설정
            .padding(start = 20.dp, end = 18.dp, top = 12.dp, bottom = 12.dp) // 내부 패딩 설정
    ) {
        Surface(
            color = OnjungTheme.colors.gray_3,
            onClick = { onLogOut() },
            interactionSource = NoRippleInteractionSource()
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 14.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "로그아웃",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = OnjungTheme.colors.text_2
                    )
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null,
                    tint = OnjungTheme.colors.text_1
                )
            }
        }

        Surface(
            color = OnjungTheme.colors.gray_3,
            onClick = { onWithdrawal() },
            interactionSource = NoRippleInteractionSource()
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 14.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "탈퇴하기",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = OnjungTheme.colors.text_2
                    )
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null,
                    tint = OnjungTheme.colors.text_1
                )
            }
        }
    }
}

private fun isNotificationPermissionGranted(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val postNotificationsPermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        )
        postNotificationsPermission == PackageManager.PERMISSION_GRANTED
    } else {
        NotificationManagerCompat.from(context).areNotificationsEnabled()
    }
}