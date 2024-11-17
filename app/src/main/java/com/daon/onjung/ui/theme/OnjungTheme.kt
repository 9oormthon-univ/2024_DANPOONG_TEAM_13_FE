package com.daon.onjung.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object OnjungTheme {
    val colors: OnjungColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: OnjungTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}