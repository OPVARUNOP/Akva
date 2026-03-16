package com.varun.akva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.intelligence.PersonalityEngine
import com.varun.akva.intelligence.VoiceConfig
import com.varun.akva.interaction.VoiceEngine
import com.varun.akva.ui.theme.*

@Composable
fun VoiceTestScreen() {
    val context = LocalContext.current
    val voiceEngine = remember { VoiceEngine(context) }
    val personalityEngine = remember { PersonalityEngine() }

    val voices = listOf(
        Triple("WhatsApp", "com.whatsapp", "Priya and Rahul are waiting. 5 messages unread."),
        Triple("Instagram", "com.instagram.android", "Instagram again. Third time today — taking a break soon?"),
        Triple("Gmail", "com.google.android.gm", "12 emails unread. A few look important."),
        Triple("YouTube", "com.google.android.youtube", "YouTube open. Make it worth your time."),
        Triple("Camera", "com.google.android.GoogleCamera", "Camera ready! Good light right now."),
        Triple("Night Mode", "night", "It is getting late. Maybe wrap up soon."),
        Triple("Stress Mode", "stress", "Slow down. Take one breath. Nothing is that urgent."),
        Triple("Morning", "morning", "Good morning Varun. You have 8 unread messages and your battery is at 75 percent.")
    )

    DisposableEffect(Unit) { onDispose { voiceEngine.shutdown() } }

    Column(
        modifier = Modifier.fillMaxSize().background(AkvaBackground).padding(24.dp).verticalScroll(rememberScrollState())
    ) {
        Text("Voice Playground", color = AkvaWhite, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Test how AKVA sounds in different contexts.", color = AkvaMuted, fontSize = 14.sp)
        Spacer(Modifier.height(24.dp))

        voices.forEach { (name, pkg, sample) ->
            val vc = when (pkg) {
                "night" -> personalityEngine.getNightVoice()
                "stress" -> personalityEngine.getStressVoice()
                "morning" -> personalityEngine.getMorningVoice()
                else -> personalityEngine.getVoiceConfig(pkg, false, false)
            }
            val isWhisper = pkg == "night"

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = AkvaSurface)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(name, color = AkvaWhite, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                        Text("P: ${vc.pitch}x  R: ${vc.speechRate}x", color = AkvaBlueWave, fontSize = 12.sp)
                        Spacer(Modifier.height(4.dp))
                        Text("\"$sample\"", color = AkvaMuted, fontSize = 12.sp, lineHeight = 16.sp)
                    }
                    Spacer(Modifier.width(12.dp))
                    Button(
                        onClick = { voiceEngine.speakImmediate(sample, vc, isWhisper) },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = AkvaBlueWave)
                    ) { Text("▶", fontSize = 18.sp, color = AkvaWhite) }
                }
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}
