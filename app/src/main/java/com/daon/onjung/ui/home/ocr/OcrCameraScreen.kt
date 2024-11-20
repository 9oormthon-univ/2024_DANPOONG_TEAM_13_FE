package com.daon.onjung.ui.home.ocr

import ReceiptConfirmBottomSheet
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.OnjungBottomSheetState
import com.daon.onjung.R
import com.daon.onjung.TopLevelDestination
import com.daon.onjung.ui.home.component.OcrFailedDialog
import com.daon.onjung.ui.home.component.OnjungSuccessDialog
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun OcrCameraScreen(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState,
    viewModel: OcrCameraViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val lensFacing = CameraSelector.LENS_FACING_BACK
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val cameraxSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
    val imageCapture = remember { ImageCapture.Builder().build() }

    var cameraProvider by remember { mutableStateOf<ProcessCameraProvider?>(null) }

    DisposableEffect(Unit) {
        bottomSheetState.setOnHideBottomSheet {
            cameraProvider?.bindToLifecycle(
                lifecycleOwner,
                cameraxSelector,
                preview,
                imageCapture
            )
        }
        onDispose {
            bottomSheetState.setOnHideBottomSheet {

            }
        }
    }

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is OcrCameraContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }

                is OcrCameraContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }

                is OcrCameraContract.Effect.ShowOcrResult -> {
                    bottomSheetState.showBottomSheet {
                        cameraProvider?.unbindAll()

                        ReceiptConfirmBottomSheet(
                            name = effect.storeName,
                            address = effect.storeAddress,
                            visitDate = effect.paymentDate,
                            spentAmount = effect.paymentAmount,
                            onConfirm = {
                                viewModel.processEvent(
                                    OcrCameraContract.Event.PostReceiptButtonClicked(
                                        effect.storeName,
                                        effect.storeAddress,
                                        effect.paymentDate,
                                        effect.paymentAmount
                                    )
                                )
                            },
                            hideBottomSheet = {
                                bottomSheetState.hideBottomSheet()
                            }
                        )
                    }
                }

                is OcrCameraContract.Effect.CameraResume -> {
                    delay(500)
                    cameraProvider?.bindToLifecycle(
                        lifecycleOwner,
                        cameraxSelector,
                        preview,
                        imageCapture
                    )
                }
            }
        }
    }

    LaunchedEffect(lensFacing) {
        cameraProvider = context.getCameraProvider().apply {
            unbindAll()
            bindToLifecycle(lifecycleOwner, cameraxSelector, preview, imageCapture)
            preview.setSurfaceProvider(previewView.surfaceProvider)
        }
    }

    BackHandler {
        if (bottomSheetState.bottomSheetState.isVisible) {
            bottomSheetState.hideBottomSheet()
        } else {
            appState.navController.navigateUp()
        }
    }

    if (uiState.isOcrErrorDialogVisible) {
        cameraProvider?.unbindAll()

        OcrFailedDialog {
            viewModel.processEvent(OcrCameraContract.Event.OcrErrorDialogDismissed)
        }
    }

    if (uiState.isPostReceiptSuccessDialogVisible) {
        if (bottomSheetState.bottomSheetState.isVisible) bottomSheetState.hideBottomSheet()

        OnjungSuccessDialog(
            onDismissRequest = {
                appState.navigateToTopLevelDestination(TopLevelDestination.Profile)
                viewModel.processEvent(OcrCameraContract.Event.PostReceiptSuccessDialogDismissed)
            },
            title = "온기를 실천해 주셔서 감사해요",
            description = "온정이 계속 함께할게요!"
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())

        if (uiState.isGuideTextVisible) {
            Text(
                text = "영수증의 처음과 끝이\n모두 포함되게 촬영해 주세요.",
                style = OnjungTheme.typography.h2.copy(color = OnjungTheme.colors.white),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(45.dp)
                    .align(Alignment.Center),
                color = OnjungTheme.colors.main_coral,
                trackColor = Color(0xFFECECEC)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(86.dp)
                .background(color = Color.Black)
                .align(Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier
                    .clickable { appState.navController.navigateUp() }
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 20.dp),
                tint = Color.White
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(86.dp)
                .background(color = Color.Black)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(56.dp)
                    .clickable {
                        captureImage(imageCapture, context) { uri ->
                            viewModel.processEvent(OcrCameraContract.Event.ImageCaptured(uri))
                        }
                    },
                painter = painterResource(id = R.drawable.ic_capture),
                contentDescription = "IC_CAPTURE",
                tint = Color.White
            )
        }
    }
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }

private fun captureImage(
    imageCapture: ImageCapture,
    context: Context,
    onSuccess: (Uri) -> Unit
) {
    val name = "onjung-${System.currentTimeMillis()}.jpg"
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            put(MediaStore.Images.Media.IS_PENDING, 1) // 임시 상태로 설정
        }
    }

    val outputOptions = ImageCapture.OutputFileOptions
        .Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
        .build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = outputFileResults.savedUri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    contentValues.clear() // IS_PENDING 플래그 제거
                    contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                    if (savedUri != null) {
                        context.contentResolver.update(savedUri, contentValues, null, null)
                    }
                }
                Log.d("CameraScreen", "Image saved successfully to gallery")
                if (savedUri != null) {
                    onSuccess(savedUri) // Uri를 성공 콜백으로 전달
                }
            }

            override fun onError(exception: ImageCaptureException) {
                Log.d("CameraScreen", "Image capture failed: ${exception.message}", exception)
            }
        })
}

