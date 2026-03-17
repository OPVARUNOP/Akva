package com.varun.akva.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.R
import com.varun.akva.ui.theme.AkvaBlack
import com.varun.akva.ui.theme.AkvaBlue
import com.varun.akva.ui.theme.AkvaGold
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    var logoAlpha by remember { mutableFloatStateOf(0f) }
    var titleAlpha by remember { mutableFloatStateOf(0f) }
    var authorAlpha by remember { mutableFloatStateOf(0f) }

    val logoAnim by animateFloatAsState(targetValue = logoAlpha, animationSpec = tween(800), label = "logo")
    val titleAnim by animateFloatAsState(targetValue = titleAlpha, animationSpec = tween(400), label = "title")
    val authorAnim by animateFloatAsState(targetValue = authorAlpha, animationSpec = tween(400), label = "author")

    LaunchedEffect(Unit) {
        logoAlpha = 1f
        delay(800)
        titleAlpha = 1f
        delay(400)
        authorAlpha = 1f
        delay(1000)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AkvaBlack),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.akva_logo),
                contentDescription = "AKVA Logo",
                modifier = Modifier
                    .size(120.dp)
                    .alpha(logoAnim)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "The Living OS",
                color = AkvaBlue,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp,
                modifier = Modifier.alpha(titleAnim)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "by Varun",
                color = AkvaGold,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp,
                modifier = Modifier.alpha(authorAnim)
            )
        }
    }
}
