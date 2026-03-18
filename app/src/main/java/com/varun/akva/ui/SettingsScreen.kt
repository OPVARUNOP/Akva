package com.varun.akva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varun.akva.data.SettingsManager
import com.varun.akva.ui.theme.DarkBackground
import com.varun.akva.ui.theme.PrimaryBlue
import androidx.compose.ui.graphics.Color

@Composable
fun SettingsScreen(s: SettingsManager) {
    Column(Modifier.fillMaxSize().background(DarkBackground).padding(16.dp)) {
        Text("SETTINGS", color = PrimaryBlue, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Proactive Speech", color = Color.White)
            Switch(s.isProactiveEnabled, { s.isProactiveEnabled = it })
        }
    }
}
