package com.daon.onjung.ui.community

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.R
import com.daon.onjung.Routes
import com.daon.onjung.ui.community.component.CommunityBanner
import com.daon.onjung.ui.community.component.CommunityTopBar
import com.daon.onjung.ui.community.component.PostCardItem
import com.daon.onjung.ui.theme.OnjungTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CommunityScreen(
    appState: OnjungAppState,
    viewModel: CommunityViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    LaunchedEffect(Unit) {
        effectFlow.collectLatest { effect ->
            when (effect) {
                is CommunityContract.Effect.NavigateTo -> {
                    appState.navigate(effect.destination, effect.navOptions)
                }
                is CommunityContract.Effect.ShowSnackBar -> {
                    appState.showSnackBar(effect.message)
                }
            }
        }
    }


    Box {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            CommunityTopBar()
            CommunityBanner()
            repeat(10) {
                PostCardItem(
                    modifier = Modifier.padding(20.dp),
                    onClick = {
                        appState.navController.navigate(Routes.Community.DETAIL)
                    }
                )
            }
        }

        WritePostButton(
            modifier = Modifier
                .align(
                    alignment = Alignment.BottomEnd
                )
                .padding(
                    end = 20.dp,
                    bottom = 25.dp
                )
        ) {
            appState.navController.navigate(Routes.Community.WRITE)
        }
    }
}

@Composable
private fun WritePostButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .size(64.dp),
        shadowElevation = 10.dp,
        shape = CircleShape,
        color = OnjungTheme.colors.main_coral,
        onClick = onClick
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "IC_PLUS",
                tint = Color.Unspecified,
            )
        }
    }
}