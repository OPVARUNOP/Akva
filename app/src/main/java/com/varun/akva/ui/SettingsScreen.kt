package com.varun.akva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.data.SettingsManager
import com.varun.akva.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    settingsManager: SettingsManager
) {
    var wakeWordEnabled by remember { mutableStateOf(settingsManager.wakeWordEnabled) }
    var nightModeEnabled by remember { mutableStateOf(settingsManager.nightModeEnabled) }
    var backendUrl by remember { mutableStateOf(settingsManager.backendUrl) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", color = AkvaWhite) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = AkvaWhite)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AkvaBlack)
            )
        },
        containerColor = AkvaBlack
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            SectionHeader("Features")
            
            SettingsToggleCard(
                title = "Wake Word (`Hey AKVA`)",
                description = "Enable always-on listening for voice control",
                checked = wakeWordEnabled,
                onCheckedChange = { 
                    wakeWordEnabled = it
                    settingsManager.wakeWordEnabled = it
                }
            )
            
            SettingsToggleCard(
                title = "Night Mode",
                description = "Whisper volume and limit responses after 10 PM",
                checked = nightModeEnabled,
                onCheckedChange = { 
                    nightModeEnabled = it
                    settingsManager.nightModeEnabled = it
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
            SectionHeader("Connection")

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, AkvaBorder, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = AkvaCard),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Backend Server URL", color = AkvaWhite, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = backendUrl,
                        onValueChange = { 
                            backendUrl = it
                            settingsManager.backendUrl = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = AkvaNavy,
                            unfocusedContainerColor = AkvaNavy,
                            focusedBorderColor = AkvaBlue,
                            unfocusedBorderColor = AkvaBorder,
                            focusedTextColor = AkvaWhite,
                            unfocusedTextColor = AkvaWhite
                        ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Default: https://web-production-d4aa5.up.railway.app",
                        color = AkvaMuted,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title.uppercase(),
        color = AkvaBlue,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
    )
}

@Composable
fun SettingsToggleCard(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(1.dp, AkvaBorder, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = AkvaCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = AkvaWhite, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(description, color = AkvaMuted, fontSize = 12.sp)
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = AkvaBlack,
                    checkedTrackColor = AkvaBlue,
                    uncheckedThumbColor = AkvaMuted,
                    uncheckedTrackColor = AkvaSurface
                )
            )
        }
    }
}
