package com.daon.onjung

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.daon.onjung.ui.component.BottomNavigationBar
import com.daon.onjung.ui.component.BottomNavigationBarItem
import com.daon.onjung.ui.theme.OnjungTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OnjungNavHost(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState
) {
    val navController = appState.navController

    ModalBottomSheetLayout(
        sheetContent = {
            bottomSheetState.bottomSheetContent.value?.invoke(this)
        },
        sheetState = bottomSheetState.bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier.navigationBarsPadding()
    ) {
        Scaffold(
            backgroundColor = Color.White,
            snackbarHost = {
                appState.scaffoldState.snackbarHostState
            },
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    OnjungBottomNavigationBar(
                        destinations = appState.topLevelDestinations,
                        currentDestination = appState.currentDestination,
                        onNavigateToDestination = { destination ->
                            appState.navigateToTopLevelDestination(destination)
                        }
                    )
                }
            }
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = Routes.Auth.Route
            ) {

            }
        }
    }
}

@Composable
private fun OnjungBottomNavigationBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    backgroundColor: Color = OnjungTheme.colors.white,
    selectedContentColor: Color = OnjungTheme.colors.main_coral,
    unselectedContentColor: Color = OnjungTheme.colors.gray_1
) {
    BottomNavigationBar(
        modifier = modifier,
        backgroundColor = backgroundColor
    ) {
        destinations.forEach { destination ->
            val isSelected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            BottomNavigationBarItem(
                label = destination.label,
                selected = isSelected,
                icon = destination.icon,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor
            ) {
                onNavigateToDestination(destination)
            }
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false