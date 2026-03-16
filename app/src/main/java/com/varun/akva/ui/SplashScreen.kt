package com.varun.akva.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    var logoVisible by remember { mutableStateOf(false) }
    var taglineVisible by remember { mutableStateOf(false) }
    var authorVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        logoVisible = true
        delay(500)
        taglineVisible = true
        delay(500)
        authorVisible = true
        delay(1000)
        onNavigate()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D1A)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(
                visible = logoVisible,
                enter = fadeIn(animationSpec = tween(800))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.akva_logo_vector),
                    contentDescription = "AKVA Logo",
                    modifier = Modifier
                        .width(280.dp)
                        .height(120.dp),
                    contentScale = ContentScale.Fit
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AnimatedVisibility(
                visible = taglineVisible,
                enter = fadeIn(animationSpec = tween(1000))
            ) {
                Text(
                    text = "The Living OS",
                    color = Color(0xFF4A90D9),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 2.sp
                )
            }
        }

        AnimatedVisibility(
            visible = authorVisible,
            enter = fadeIn(animationSpec = tween(800)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
        ) {
            Text(
                text = "by Varun",
                color = Color(0xFFF5A623),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
