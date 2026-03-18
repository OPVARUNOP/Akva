package com.varun.akva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.data.ActionHistory

@Composable
fun ActionsScreen(actions: List<ActionHistory>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00080D))
            .padding(16.dp)
    ) {
        Text(
            text = "◈ MISSION LOG",
            color = Color(0xFF00D4FF),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(actions) { action ->
                ActionItem(action)
            }
        }
    }
}

@Composable
fun ActionItem(action: ActionHistory) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF001520)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "> ${action.action}",
                    color = Color(0xFF00FF88),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp
                )
                Text(
                    text = if (action.success) "SUCCESS" else "FAILED",
                    color = if (action.success) Color(0xFF00FF88) else Color.Red,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
            if (action.target.isNotEmpty()) {
                Text(
                    text = "TARGET: ${action.target}",
                    color = Color(0xFF007A99),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
            Text(
                text = action.result,
                color = Color(0xFF8FFCFF),
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
