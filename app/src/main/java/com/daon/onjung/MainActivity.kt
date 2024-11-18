package com.daon.onjung

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.daon.onjung.ui.theme.OnjungTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            OnjungTheme {
                OnjungNavHost(
                    appState = rememberOnjungAppState(),
                    bottomSheetState = rememberOnjungBottomSheetState()
                )
            }
        }
    }
}
