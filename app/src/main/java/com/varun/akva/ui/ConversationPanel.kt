package com.varun.akva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.data.ConversationMemory
import kotlinx.coroutines.launch

@Composable
fun ConversationPanel(history: List<ConversationMemory>, currentTranscript: String) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(history.size, currentTranscript) {
        if (history.isNotEmpty()) {
            scope.launch { listState.animateScrollToItem(history.size - 1) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color(0xFF010C10))
            .padding(12.dp)
    ) {
        Text(
            text = "◈ COMMS_TERMINAL",
            color = Color(0xFF007A99),
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f)
        ) {
            items(history) { turn ->
                Text(
                    text = "YOU: ${turn.userSaid}",
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = "AI: ${turn.akvaReplied}",
                    color = Color(0xFF00D4FF),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
            if (currentTranscript.isNotEmpty()) {
                item {
                    Text(
                        text = "LIVE: $currentTranscript...",
                        color = Color(0xFF00FF88),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp,
                    )
                }
            }
        }
    }
}
