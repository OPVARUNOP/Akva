package com.varun.akva.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.R
import com.varun.akva.data.SettingsManager
import com.varun.akva.ui.theme.*

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val settings = remember { SettingsManager(context) }

    var masterEnabled by remember { mutableStateOf(settings.masterVoiceEnabled) }
    var hapticEnabled by remember { mutableStateOf(settings.hapticEnabled) }
    var screenMoodEnabled by remember { mutableStateOf(settings.screenMoodEnabled) }
    var wakeWordEnabled by remember { mutableStateOf(settings.wakeWordEnabled) }
    var morningEnabled by remember { mutableStateOf(settings.morningBriefingEnabled) }
    var weeklyEnabled by remember { mutableStateOf(settings.weeklyStoryEnabled) }
    var settingsGuideEnabled by remember { mutableStateOf(settings.settingsGuideEnabled) }
    var backendUrl by remember { mutableStateOf(settings.backendUrl) }
    var silentStart by remember { mutableStateOf(settings.silentHourStart.toString()) }
    var silentEnd by remember { mutableStateOf(settings.silentHourEnd.toString()) }
    var showClearDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AkvaBackground)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(AkvaDarkNavy)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.akva_logo_vector),
                contentDescription = "AKVA Logo",
                modifier = Modifier.width(80.dp).height(40.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.width(16.dp))
            Text("Settings", color = AkvaWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(24.dp))

            // Feature toggles
            SettingsToggle("Master Voice", "Global speech on/off", masterEnabled) { masterEnabled = it; settings.masterVoiceEnabled = it }
            SettingsToggle("Haptic Feedback", "Vibration patterns", hapticEnabled) { hapticEnabled = it; settings.hapticEnabled = it }
            SettingsToggle("Screen Mood", "Time-based color overlays", screenMoodEnabled) { screenMoodEnabled = it; settings.screenMoodEnabled = it }
            SettingsToggle("Wake Word", "\"Hey AKVA\" detection", wakeWordEnabled) { wakeWordEnabled = it; settings.wakeWordEnabled = it }
            SettingsToggle("Morning Briefing", "Daily summary on first unlock", morningEnabled) { morningEnabled = it; settings.morningBriefingEnabled = it }
            SettingsToggle("Weekly Story", "Sunday evening narrative", weeklyEnabled) { weeklyEnabled = it; settings.weeklyStoryEnabled = it }
            SettingsToggle("Settings Guide", "Explain Android settings screens", settingsGuideEnabled) { settingsGuideEnabled = it; settings.settingsGuideEnabled = it }

            Spacer(Modifier.height(24.dp))
            Text("Silent Hours", color = AkvaOnSurface, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(value = silentStart, onValueChange = {
                    silentStart = it; it.toIntOrNull()?.let { h -> if (h in 0..23) settings.silentHourStart = h }
                }, label = { Text("Start (0-23)", color = AkvaMuted) }, modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = AkvaMuted, focusedBorderColor = AkvaBlueWave,
                        focusedTextColor = AkvaWhite, unfocusedTextColor = AkvaWhite
                    ))
                OutlinedTextField(value = silentEnd, onValueChange = {
                    silentEnd = it; it.toIntOrNull()?.let { h -> if (h in 0..23) settings.silentHourEnd = h }
                }, label = { Text("End (0-23)", color = AkvaMuted) }, modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = AkvaMuted, focusedBorderColor = AkvaBlueWave,
                        focusedTextColor = AkvaWhite, unfocusedTextColor = AkvaWhite
                    ))
            }

            Spacer(Modifier.height(24.dp))
            Text("Backend Server", color = AkvaOnSurface, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = backendUrl, onValueChange = { backendUrl = it; settings.backendUrl = it },
                modifier = Modifier.fillMaxWidth(), singleLine = true,
                label = { Text("Server URL", color = AkvaMuted) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = AkvaMuted, focusedBorderColor = AkvaBlueWave,
                    focusedTextColor = AkvaWhite, unfocusedTextColor = AkvaWhite
                )
            )

            Spacer(Modifier.height(32.dp))
            OutlinedButton(
                onClick = { showClearDialog = true },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Delete, null, tint = AkvaAccentRed, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Clear Learned Data", color = AkvaAccentRed)
            }

            Spacer(Modifier.height(24.dp))
            Text("AKVA v1.0 — by Varun", color = AkvaMuted, fontSize = 12.sp)
            Spacer(Modifier.height(24.dp))
        }
    }

    if (showClearDialog) {
        AlertDialog(
            onDismissRequest = { showClearDialog = false },
            title = { Text("Clear Data?") },
            text = { Text("This will reset morning briefing and weekly story schedules.") },
            confirmButton = { TextButton(onClick = { settings.clearLearnedData(); showClearDialog = false }) { Text("Clear", color = AkvaBlueWave) } },
            dismissButton = { TextButton(onClick = { showClearDialog = false }) { Text("Cancel", color = AkvaBlueWave) } }
        )
    }
}

@Composable
fun SettingsToggle(title: String, subtitle: String, checked: Boolean, onToggle: (Boolean) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = AkvaSurface)
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(title, color = AkvaWhite, fontWeight = FontWeight.Medium, fontSize = 15.sp)
                Text(subtitle, color = AkvaMuted, fontSize = 12.sp)
            }
            Switch(
                checked = checked, 
                onCheckedChange = onToggle,
                colors = SwitchDefaults.colors(checkedTrackColor = AkvaBlueWave, checkedThumbColor = AkvaWhite)
            )
        }
    }
}
