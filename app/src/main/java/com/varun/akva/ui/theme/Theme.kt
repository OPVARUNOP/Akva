package com.varun.akva.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AkvaDarkScheme = darkColorScheme(
    primary = AkvaBlueWave,
    secondary = AkvaDeepBlue,
    tertiary = AkvaGold,
    background = AkvaBackground,
    surface = AkvaSurface,
    onPrimary = AkvaWhite,
    onSecondary = AkvaWhite,
    onBackground = AkvaOnSurface,
    onSurface = AkvaOnSurface
)

@Composable
fun AKVATheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = AkvaDarkScheme, content = content)
}
