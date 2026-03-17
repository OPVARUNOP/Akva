package com.varun.akva.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    background = AkvaBlack,
    surface = AkvaSurface,
    primary = AkvaBlue,
    onPrimary = AkvaWhite,
    onBackground = AkvaWhite,
    onSurface = AkvaWhite
)

@Composable
fun AKVATheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = AkvaBlack.toArgb()
            window.navigationBarColor = AkvaBlack.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
