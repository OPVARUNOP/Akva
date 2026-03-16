package com.varun.akva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.data.AppUsageEvent
import com.varun.akva.data.AkvaRepository
import com.varun.akva.intelligence.ContextDetector
import com.varun.akva.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ActivityLogScreen() {
    val context = LocalContext.current
    val repository = remember { AkvaRepository(context) }
    val contextDetector = remember { ContextDetector(context) }
    var events by remember { mutableStateOf<List<AppUsageEvent>>(emptyList()) }
    val dateFormat = remember { SimpleDateFormat("HH:mm  MMM dd", Locale.US) }

    LaunchedEffect(Unit) {
        events = withContext(Dispatchers.IO) { repository.getRecentEvents(20) }
    }

    Column(modifier = Modifier.fillMaxSize().background(AkvaBackground).padding(horizontal = 24.dp)) {
        Spacer(Modifier.height(24.dp))
        Text("Activity Log", color = AkvaOnSurface, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Recent AKVA interactions", color = AkvaMuted, fontSize = 14.sp)
        Spacer(Modifier.height(16.dp))

        if (events.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No events yet. AKVA will log interactions here.", color = AkvaMuted, fontSize = 14.sp)
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(events) { event ->
                    val appName = contextDetector.getAppName(event.packageName)
                    val time = dateFormat.format(Date(event.timestamp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = AkvaSurface)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(appName, color = AkvaWhite, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, modifier = Modifier.weight(1f))
                                if (event.isNightUsage) {
                                    Text("🌙", fontSize = 14.sp)
                                    Spacer(Modifier.width(6.dp))
                                }
                                if (event.stressScore >= 5) {
                                    Box(
                                        modifier = Modifier.background(AkvaAccentRed.copy(alpha = 0.2f), RoundedCornerShape(6.dp)).padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) { Text("S:${event.stressScore}", color = AkvaAccentRed, fontSize = 11.sp) }
                                }
                            }
                            Spacer(Modifier.height(4.dp))
                            if (event.spokenText.isNotBlank()) {
                                Text("\"${event.spokenText}\"", color = AkvaMuted, fontSize = 12.sp,
                                    maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = 16.sp)
                                Spacer(Modifier.height(4.dp))
                            }
                            Text(time, color = AkvaMuted.copy(alpha = 0.6f), fontSize = 11.sp)
                        }
                    }
                }
                item { Spacer(Modifier.height(16.dp)) }
            }
        }
    }
}
