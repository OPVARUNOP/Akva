package com.varun.akva.ui

import androidx.compose.animation.core.*
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            painter = painterResource(id = R.drawable.akva_logo), // using akva_logo since vector might not exist
            contentDescription = "AKVA Logo",
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "The Living OS",
            color = AkvaBlue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "by Varun",
            color = AkvaGold,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        // Middle Section (Wave)
        AnimatedSoundWave(isSpeaking = isAlive)

        Spacer(modifier = Modifier.height(24.dp))

        // Status
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { if (!isAlive) onSetupClick() }
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(if (isAlive) AkvaGreen else AkvaRed)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (isAlive) "Alive and listening" else "Needs permissions (Tap to setup)",
                color = AkvaWhite,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Section (3 Cards)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            HomeCard(
                icon = Icons.Default.Mic,
                title = "Say Hey AKVA",
                onClick = onManualMicClick
            )
            HomeCard(
                icon = Icons.Default.Settings,
                title = "Settings",
                onClick = onSettingsClick
            )
            HomeCard(
                icon = Icons.Default.List,
                title = "Activity Log",
                onClick = onActivityLogClick
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Open any app and I will speak",
            color = AkvaMuted,
            fontSize = 12.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun AnimatedSoundWave(isSpeaking: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val heights = (0..4).map { i ->
        if (isSpeaking) {
            infiniteTransition.animateFloat(
                initialValue = 16f,
                targetValue = 64f,
                animationSpec = infiniteRepeatable(
                    animation = tween(400 + (i * 100), easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "bar_$i"
            ).value
        } else {
            16f
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.height(80.dp)
    ) {
        heights.forEach { height ->
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .height(height.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(AkvaBlue)
            )
        }
    }
}

@Composable
fun HomeCard(icon: ImageVector, title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(AkvaCard)
            .border(1.dp, AkvaBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = AkvaBlue,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            color = AkvaWhite,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}
