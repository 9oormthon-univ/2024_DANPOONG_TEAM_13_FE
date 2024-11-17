package com.daon.onjung.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

fun onjungColors() = OnjungColors()
fun onjungFonts() = OnjungTypography()

@Composable
fun OnjungTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: OnjungColors = onjungColors(),
    darkColors: OnjungColors = onjungColors(),
    typography: OnjungTypography = onjungFonts(),
    content: @Composable () -> Unit
) {
    val currentColor = remember { if (darkTheme) darkColors else colors }
    val rememberedColors = remember { currentColor.copy() }.apply { updateColorFrom(currentColor) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides typography
    ) {
        ProvideTextStyle(typography.body1, content = content)
    }

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.white.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
}