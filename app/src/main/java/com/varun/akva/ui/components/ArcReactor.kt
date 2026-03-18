package com.varun.akva.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.varun.akva.ui.theme.PrimaryBlue

@Composable
fun ArcReactor() {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.8f, targetValue = 1.2f,
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Reverse), label = "pulse"
    )

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(200.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = size / 2f
            val centerOffset = Offset(center.width, center.height)
            val radius = size.minDimension / 2.5f

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(PrimaryBlue.copy(alpha = 0.4f * pulse), Color.Transparent),
                    center = centerOffset,
                    radius = radius * 1.5f
                )
            )
            drawCircle(PrimaryBlue, radius * 0.8f, style = Stroke(4f), alpha = 0.8f)
            drawCircle(PrimaryBlue, radius * pulse, style = Stroke(2f), alpha = 0.5f)
        }
    }
}
