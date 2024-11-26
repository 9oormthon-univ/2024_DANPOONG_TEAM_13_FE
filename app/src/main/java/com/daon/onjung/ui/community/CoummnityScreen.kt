package com.daon.onjung.ui.community

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daon.onjung.OnjungAppState
import com.daon.onjung.Routes
import com.daon.onjung.ui.community.component.CommunityBanner
import com.daon.onjung.ui.community.component.CommunityTopBar
import com.daon.onjung.ui.community.component.PostCardItem
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


    Column (
        modifier = Modifier.verticalScroll(rememberScrollState())
    ){
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

}