package com.varun.akva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.data.UserProfile

@Composable
fun MemoryScreen(profile: UserProfile?, onUpdate: (UserProfile) -> Unit) {
    var name by remember { mutableStateOf(profile?.userName ?: "") }
    var preferredName by remember { mutableStateOf(profile?.preferredName ?: "") }
    var routine by remember { mutableStateOf(profile?.dailyRoutine ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00080D))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "◈ CORE PROTOCOLS",
            color = Color(0xFF00D4FF),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(24.dp))

        MemoryField("USER_ID", name) { name = it }
        MemoryField("DESIGNATION", preferredName) { preferredName = it }
        MemoryField("DAILY_ROUTINE", routine) { routine = it }

        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { 
                onUpdate(UserProfile(userName = name, preferredName = preferredName, dailyRoutine = routine))
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007A99)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("UPDATE SYSTEM MEMORY", fontFamily = FontFamily.Monospace)
        }
    }
}

@Composable
fun MemoryField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            color = Color(0xFF007A99),
            fontSize = 12.sp,
            fontFamily = FontFamily.Monospace
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF001520),
                focusedContainerColor = Color(0xFF001520),
                unfocusedTextColor = Color(0xFF8FFCFF),
                focusedTextColor = Color(0xFF8FFCFF),
                cursorColor = Color(0xFF00D4FF)
            ),
            textStyle = LocalTextStyle.current.copy(fontFamily = FontFamily.Monospace)
        )
    }
}
