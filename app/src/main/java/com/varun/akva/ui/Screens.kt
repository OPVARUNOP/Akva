package com.varun.akva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.varun.akva.data.UserProfile
import com.varun.akva.ui.theme.*

@Composable
fun MemoryScreen(p: UserProfile?) {
    Column(Modifier.fillMaxSize().background(DarkBackground).padding(16.dp)) {
        Text("MEMORY CORE", color = PrimaryBlue, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))
        Text("No facts recorded.", color = Color.Gray)
    }
}

@Composable
fun ActionsScreen(a: List<com.varun.akva.data.ActionHistory>) {
    Column(Modifier.fillMaxSize().background(DarkBackground).padding(16.dp)) {
        Text("ACTION LOG", color = PrimaryBlue, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))
        Text("No actions logged.", color = Color.Gray)
    }
}
