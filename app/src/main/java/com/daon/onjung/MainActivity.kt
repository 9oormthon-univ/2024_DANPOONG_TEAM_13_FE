package com.daon.onjung

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.daon.onjung.ui.theme.OnjungTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val appState = rememberOnjungAppState()
            val bottomSheetState = rememberOnjungBottomSheetState()
            this.navController = appState.navController

            OnjungTheme {
                OnjungNavHost(
                    appState = appState,
                    bottomSheetState = bottomSheetState
                )
            }

            LaunchedEffect(Unit) {
                handleIntent(intent)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        setIntent(intent) // Intent를 최신 상태로 설정
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val uri = intent.data
        if (uri != null) {
            Log.d("debugging", "Handle Intent URI: $uri")
            if (::navController.isInitialized) {
                if (!navController.handleDeepLink(intent)) {
                    Log.e("debugging", "Failed to handle deep link: $uri")
                }
            } else {
                Log.e("debugging", "NavController is not initialized yet")
            }
        } else {
            Log.w("debugging", "No URI found in the intent")
        }
    }
}
