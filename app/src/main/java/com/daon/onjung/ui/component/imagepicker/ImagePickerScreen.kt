package com.daon.onjung.ui.component.imagepicker

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.R
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun ImagePickerScreen(
    modifier: Modifier = Modifier,
    maxImgCount: Int = 1,
    onClosed: () -> Unit,
    onSelected: (List<Uri>) -> Unit
) {
    val context = LocalContext.current
    val viewModel: ImageViewModel = viewModel(
        factory = ImageViewModelFactory(
            repository = ImageRepository(context = context)
        )
    )

    BackHandler {
        onClosed()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OnjungTheme.colors.gray_3)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBar(
            content = "최근 항목",
            rightIcon = null,
            leftIconOnClick = {
                viewModel.clearImages()
                onClosed()
            }
        )
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            PickerContent(
                modifier = modifier.fillMaxSize(),
                insertImage = viewModel::insertImage,
                deleteImage = viewModel::deleteImage,
                images = viewModel.images,
                selectedImages = viewModel.selectedImages,
                selectImage = viewModel::selectImage,
                selectImageFromUri = viewModel::selectImageFromUri,
                removeImage = viewModel::removeImage,
                maxImgCount = maxImgCount
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .background(color = Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(40.dp))

                FilledWidthButton(
                    text = "완료",
                    isEnabled = viewModel.selectedImages.isNotEmpty(),
                    onClick = {
                        onSelected(viewModel.selectedImages.map { it.contentUri })
                        viewModel.clearImages()
                    }
                )
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Composable
internal fun PickerContent(
    modifier: Modifier = Modifier,
    images: List<ImageInfo>,
    selectedImages: List<ImageInfo>,
    insertImage: () -> Uri?,
    deleteImage: (Uri?) -> Unit,
    selectImage: (ImageInfo, Int) -> Unit,
    selectImageFromUri: (Uri, Int) -> Unit,
    removeImage: (ImageInfo) -> Unit,
    maxImgCount: Int,
) {
    var cameraUri: Uri? = null

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                cameraUri?.let {
                    selectImageFromUri(it, maxImgCount)
                }
            } else {
                deleteImage(cameraUri)
            }
        }

    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(3)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1.0f)
                    .clickable {
                        cameraUri = insertImage()
                        cameraUri?.let { cameraLauncher.launch(it) }
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera_baseline_30),
                        tint = Color.Unspecified,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "사진 찍기",
                        style = OnjungTheme.typography.body2.copy(
                            color = OnjungTheme.colors.main_coral
                        )
                    )
                }
            }
        }

        items(images) { image ->
            ImageItem(
                image = image,
                selectedImages = selectedImages,
                selectImage = selectImage,
                removeImage = removeImage,
                maxImgCount = maxImgCount
            )
        }
    }
}

@Composable
internal fun ImageItem(
    image: ImageInfo,
    selectedImages: List<ImageInfo>,
    selectImage: (ImageInfo, Int) -> Unit,
    removeImage: (ImageInfo) -> Unit,
    maxImgCount: Int
) {
    val context = LocalContext.current

    val selected = selectedImages.any { it.id == image.id }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    if (selected) {
                        removeImage(image)
                    } else {
                        if (selectedImages.size <= maxImgCount) {
                            selectImage(image, maxImgCount)
                        }
                    }
                }
            ),
        contentAlignment = Alignment.TopEnd
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(image.contentUri)
                .build(),
            contentDescription = "IMG_URL",
            filterQuality = FilterQuality.Low,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1.0F)
                .border(
                    width = 1.dp,
                    color = if (selected) OnjungTheme.colors.main_coral else Color.Transparent
                )
        )

        if (selected) {
            ImageIndicator(imgCount = selectedImages.indexOf(image) + 1)
        }
    }
}

@Composable
internal fun ImageIndicator(
    imgCount: Int
) {
    Surface(
        modifier = Modifier
            .padding(10.dp)
            .size(24.dp),
        shape = CircleShape,
        color = OnjungTheme.colors.main_coral
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "$imgCount",
                color = Color.White,
                fontSize = 14.sp,
            )
        }
    }
}