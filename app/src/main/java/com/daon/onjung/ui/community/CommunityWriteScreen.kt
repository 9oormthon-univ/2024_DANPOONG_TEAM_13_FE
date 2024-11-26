package com.daon.onjung.ui.community

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.ui.component.OTextField
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CommunityWriteScreen(
    appState: OnjungAppState,
    viewModel: CommunityWriteViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

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
                    imageUrl = null,
                    onClick = { }
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
                    value = uiState.content,
                    onValueChange = viewModel::updateContent,
                    maxLength = uiState.contentMaxLength,
                    placeholderText = uiState.contentPlaceholderText
                )

                Spacer(modifier = Modifier.height(200.dp))
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

            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun CommunityWritePickPhotoButton(
    imageUrl: String?,
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
        Surface(
            shape = RoundedCornerShape(100.dp),
            color = Color.White
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