package com.varun.akva.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.R
import com.varun.akva.services.AKVAAccessibilityService
import com.varun.akva.ui.theme.*

@Composable
fun AkvaLogo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.akva_logo_vector),
            contentDescription = "AKVA Logo",
            modifier = Modifier
                .width(280.dp)
                .height(120.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "The Living OS",
            fontSize = 14.sp,
            color = Color(0xFF4A90D9),
            fontWeight = FontWeight.Light,
            letterSpacing = 2.sp
        )
    }
}

@Composable
fun AnimatedSoundWave(isSpeaking: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val heights = (0..4).map { i ->
        if (isSpeaking) {
            infiniteTransition.animateFloat(
                initialValue = 10f,
                targetValue = 40f,
                animationSpec = infiniteRepeatable(
                    animation = tween(400 + (i * 100), easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "bar_$i"
            ).value
        } else {
            10f
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.height(50.dp)
    ) {
        heights.forEach { height ->
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .height(height.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color(0xFF4A90D9))
            )
        }
    }
}

@Composable
fun HomeScreen(
    onSetupClick: () -> Unit,
    onTestVoice: () -> Unit,
    onMorningBrief: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val isAlive = AKVAAccessibilityService.isRunning

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D1A))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        
        AkvaLogo()
        
        Spacer(modifier = Modifier.weight(1f))
        
        AnimatedSoundWave(isSpeaking = isAlive)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(if (isAlive) Color(0xFF22C55E) else Color(0xFFEF4444))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (isAlive) "AKVA is alive and listening" else "Permissions needed",
                color = AkvaOnSurface,
                fontSize = 16.sp
            )
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        if (!isAlive) {
            Button(
                onClick = onSetupClick,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AkvaAccentRed)
            ) {
                Text("Complete Setup", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        } else {
            OutlinedButton(
                onClick = onTestVoice,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text("Test Voice", fontSize = 16.sp, color = AkvaBlueWave)
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onMorningBrief,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text("Morning Brief", fontSize = 16.sp, color = AkvaBlueWave)
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onSettingsClick,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text("Settings", fontSize = 16.sp, color = AkvaBlueWave)
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}
