package com.varun.akva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.varun.akva.data.ConversationMemory
import com.varun.akva.ui.components.ArcReactor
import com.varun.akva.ui.theme.*

@Composable
fun HomeScreen(h: List<ConversationMemory>) {
    Column(Modifier.fillMaxSize().background(DarkBackground).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(40.dp)); ArcReactor(); Spacer(Modifier.height(20.dp))
        Text("SYSTEM: ACTIVE", color = PrimaryBlue, style = MaterialTheme.typography.labelSmall)
        Box(Modifier.weight(1f).padding(top = 20.dp)) {
            LazyColumn(reverseLayout = true) {
                items(h) { m ->
                    Text("[USR]: ${m.userSaid}", color = Color.White)
                    Text("[AKV]: ${m.akvaReplied}", color = PrimaryBlue)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}
