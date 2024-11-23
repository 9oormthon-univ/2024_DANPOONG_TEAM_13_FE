package com.daon.onjung

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.daon.onjung.network.model.StoreCategory
import com.daon.onjung.ui.CelebrationScreen
import com.daon.onjung.ui.auth.authGraph
import com.daon.onjung.ui.component.BottomNavigationBar
import com.daon.onjung.ui.component.BottomNavigationBarItem
import com.daon.onjung.ui.donation.donationGraph
import com.daon.onjung.ui.home.homeGraph
import com.daon.onjung.ui.mail.mailGraph
import com.daon.onjung.ui.profile.profileGraph
import com.daon.onjung.ui.setting.settingGraph
import com.daon.onjung.ui.theme.OnjungTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OnjungNavHost(
    appState: OnjungAppState,
    bottomSheetState: OnjungBottomSheetState,
) {
    val navController = appState.navController

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Color.White
        )
        systemUiController.setNavigationBarColor(
            color = Color.Black
        )
    }

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
                settingGraph(
                    appState = appState,
                    bottomSheetState = bottomSheetState
                )
                donationGraph(
                    appState = appState,
                    bottomSheetState = bottomSheetState
                )

                composable(
                    route = Routes.CELEBRATION,
                    arguments = listOf(
                        navArgument("storeName") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument("address") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument("category") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument("userName") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument("expirationDate") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument("logoImgUrl") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    ),
                    deepLinks = listOf(
                        navDeepLink {
                            uriPattern = "app://daon.onjung?storeName={storeName}&address={address}&category={category}&userName={userName}&expirationDate={expirationDate}&logoImgUrl={logoImgUrl}"
                            action = Intent.ACTION_VIEW
                        }
                    )
                ) { entry ->
                    val storeName = entry.arguments?.getString("storeName") ?: ""
                    val address = entry.arguments?.getString("address") ?: ""
                    val categoryString = entry.arguments?.getString("category") ?: ""
                    val category = runCatching { StoreCategory.valueOf(categoryString) }.getOrElse { StoreCategory.KOREAN }
                    val userName = entry.arguments?.getString("userName") ?: ""
                    val expirationDate = entry.arguments?.getString("expirationDate") ?: ""
                    val logoImgUrl = entry.arguments?.getString("logoImgUrl") ?: ""

                    CelebrationScreen(
                        name = storeName,
                        logoImgUrl = logoImgUrl,
                        address = address,
                        category = category,
                        userName = userName,
                        expirationDate = expirationDate
                    )
                }
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