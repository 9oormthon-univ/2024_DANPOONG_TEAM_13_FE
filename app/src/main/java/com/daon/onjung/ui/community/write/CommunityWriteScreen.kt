package com.daon.onjung.ui.community.write

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.ui.component.OTextField
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.component.imagepicker.ImagePickerContract
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CommunityWriteScreen(
    appState: OnjungAppState,
    viewModel: CommunityWriteViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val context = LocalContext.current

    val permissions = arrayOf(
        Manifest.permission.CAMERA,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
    )
    val allPermissionsGranted = permissions.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ImagePickerContract(),
        onResult = { uris ->
            uris?.let {
                viewModel.updateSelectedImage(it[0])
            }
        }
    )

    val launchMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionMap ->
        val areGranted = permissionMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            imagePicker.launch(1)
        } else {
            appState.showSnackBar("미디어 권한과 카메라 권한을 허용해야 갤러리를 사용할 수 있습니다")

            // 허용하지 않았을 경우 설정창으로 이동
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", context.packageName, null)
                intent.data = uri
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(context, intent, null)
            }, 500)
        }
    }

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is CommunityWriteContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is CommunityWriteContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .imePadding()
        ) {
            TopBar(
                "작성하기",
                rightIcon = null,
                leftIconOnClick = { }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                CommunityWritePickPhotoButton(
                    imageUrl = uiState.selectedImgUri,
                    onClick = {
                        if (allPermissionsGranted) {
                            imagePicker.launch(1)
                        } else {
                            launchMultiplePermissions.launch(permissions)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                OTextField(
                    value = uiState.title,
                    onValueChange = viewModel::updateTitle,
                    maxLength = uiState.titleMaxLength,
                    textStyle = OnjungTheme.typography.h1.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    placeholderText = uiState.titlePlaceholderText
                )

                Spacer(modifier = Modifier.height(12.dp))

                OTextField(
                    modifier = Modifier.padding(
                        bottom = 200.dp
                    ),
                    value = uiState.content,
                    onValueChange = viewModel::updateContent,
                    maxLength = uiState.contentMaxLength,
                    placeholderText = uiState.contentPlaceholderText
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(Alignment.BottomCenter)
        ) {
            FilledWidthButton(
                text = "등록하기"
            ) {
                viewModel.processEvent(CommunityWriteContract.Event.UploadPost)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun CommunityWritePickPhotoButton(
    imageUrl: Uri?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(
                color = OnjungTheme.colors.gray_2,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl).build(),
            contentDescription = "image",
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            modifier = Modifier.fillMaxSize().clip(
                RoundedCornerShape(12.dp)
            )
        )

        Surface(
            shape = RoundedCornerShape(100.dp),
            color = Color.White,
            onClick = onClick
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 14.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_photo),
                    contentDescription = "ic_photo",
                    tint = Color.Unspecified
                )

                Text(
                    "사진 선택하기",
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            }
        }
    }
}