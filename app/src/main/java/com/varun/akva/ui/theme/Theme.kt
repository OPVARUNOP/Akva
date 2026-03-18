package com.varun.akva.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun AKVATheme(c: @Composable () -> Unit) {
    MaterialTheme(colorScheme = darkColorScheme(primary = PrimaryBlue, background = DarkBackground), typography = Typography, content = c)
}
