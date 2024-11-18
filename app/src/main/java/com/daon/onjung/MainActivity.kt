package com.daon.onjung

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.daon.onjung.ui.component.YoutubeScreen
import com.daon.onjung.ui.theme.OnjungTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            OnjungTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = OnjungTheme.colors.white
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        YoutubeScreen(
                            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                            youtubeVideoId = "FgAL6T_KILw",
                            lifecycleOwner = this@MainActivity
                        )

                    }
                }
            }
        }
    }
}
