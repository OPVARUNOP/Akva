package com.varun.akva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.data.AkvaRepository
import com.varun.akva.data.AppUsageEvent
import com.varun.akva.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityLogScreen(
    onBack: () -> Unit,
    repository: AkvaRepository
) {
    val logs by repository.getRecentEvents(100).collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Activity Log", color = AkvaWhite) },
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
        if (logs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No recent activity.", color = AkvaMuted, fontSize = 16.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(logs) { log ->
                    LogItemCard(log)
                }
            }
        }
    }
}

@Composable
fun LogItemCard(log: AppUsageEvent) {
    val formatter = SimpleDateFormat("hh:mm a, MMM dd", Locale.getDefault())
    val timeString = formatter.format(Date(log.timestamp))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, AkvaBorder, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = AkvaCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = log.packageName.substringAfterLast('.').capitalize(),
                    color = AkvaBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = timeString,
                    color = AkvaMuted,
                    fontSize = 12.sp
                )
            }
            if (!log.aiResponse.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Icon(
                        imageVector = Icons.Default.ChatBubbleOutline,
                        contentDescription = null,
                        tint = AkvaMuted,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = log.aiResponse,
                        color = AkvaWhite,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}
