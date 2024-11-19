package com.daon.onjung

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.daon.onjung.ui.auth.authGraph
import com.daon.onjung.ui.component.BottomNavigationBar
import com.daon.onjung.ui.component.BottomNavigationBarItem
import com.daon.onjung.ui.home.homeGraph
import com.daon.onjung.ui.mail.mailGraph
import com.daon.onjung.ui.profile.profileGraph
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
            backgroundColor = OnjungTheme.colors.gray_3,
            snackbarHost = {
                OnjungSnackBar(appState = appState)
            },
            bottomBar = {
                AnimatedVisibility(
                    appState.shouldShowBottomBar,
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it },
                ) {
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
                startDestination = Routes.Auth.ROUTE
            ) {
                authGraph(
                    appState = appState
                )

                homeGraph(
                    appState = appState,
                    bottomSheetState = bottomSheetState
                )
                mailGraph(
                    appState = appState,
                    bottomSheetState = bottomSheetState
                )
                profileGraph(
                    appState = appState,
                    bottomSheetState = bottomSheetState
                )
            }
        }
    }
}

@Composable
private fun OnjungSnackBar(appState: OnjungAppState) {
    SnackbarHost(hostState = appState.scaffoldState.snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(
                    bottom = 20.dp,
                    start = 20.dp,
                    end = 20.dp
                )
            ) {
                Text(text = data.message)
            }
        }
    )
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