package com.varun.akva.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.R
import com.varun.akva.services.AKVAAccessibilityService
import com.varun.akva.ui.theme.*

@Composable
fun HomeScreen(
    onSetupClick: () -> Unit,
    onManualMicClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onActivityLogClick: () -> Unit
) {
    val isAlive = AKVAAccessibilityService.isRunning

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AkvaBlack)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        // Top Section
        Image(
            painter = painterResource(id = R.drawable.akva_logo),
            contentDescription = "AKVA Logo",
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "AKVA ULTIMATE",
            color = AkvaBlue,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
        Text(
            text = "MARK XXX INTERFACE",
            color = AkvaGold,
            fontSize = 10.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        // Middle Section (JARVIS Pulsing Rings)
        JarvisPulseIndicator(isSpeaking = isAlive)

        Spacer(modifier = Modifier.weight(1f))

        // Status
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { if (!isAlive) onSetupClick() }
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(if (isAlive) Color(0xFF00FF88) else Color.Red)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (isAlive) "SYSTEM ONLINE" else "INITIALISATION REQUIRED",
                color = AkvaWhite,
                fontSize = 12.sp,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun JarvisPulseIndicator(isSpeaking: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isSpeaking) 1.2f else 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = if (isSpeaking) 0.8f else 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(240.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = this.center
            val radius = size.minDimension / 2
            
            // Draw 3 concentric rings with different rotations
            for (i in 1..3) {
                drawCircle(
                    color = Color(0xFF00D4FF).copy(alpha = alpha / i),
                    radius = radius * 0.8f * (i / 3f) * scale,
                    center = center,
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }
        
        // Inner core
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF00D4FF).copy(alpha = 0.2f))
                .border(2.dp, Color(0xFF00D4FF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AKVA",
                color = Color(0xFF00D4FF),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
