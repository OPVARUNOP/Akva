package com.varun.akva.ui

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionsScreen(
    onBack: () -> Unit,
    permsStatus: Map<String, Boolean>,
    onRequestPermission: (String) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Permissions", color = AkvaWhite) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = AkvaWhite)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AkvaBlack
                )
            )
        },
        containerColor = AkvaBlack
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Let AKVA Come Alive",
                color = AkvaWhite,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Grant these permissions to enable full phone control and AI observation.",
                color = AkvaMuted,
                fontSize = 14.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            PermissionCard(
                title = "Accessibility",
                description = "Lets AKVA observe and control apps",
                isGranted = permsStatus["accessibility"] == true,
                onClick = {
                    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            )

            PermissionCard(
                title = "Notification Access",
                description = "Lets AKVA read and announce messages",
                isGranted = permsStatus["notification"] == true,
                onClick = {
                    val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            )

            PermissionCard(
                title = "Microphone",
                description = "For Wake Word and Conversation",
                isGranted = permsStatus["microphone"] == true,
                onClick = { onRequestPermission("microphone") }
            )

            PermissionCard(
                title = "Phone & Contacts",
                description = "To answer calls and send messages",
                isGranted = permsStatus["phone"] == true,
                onClick = { onRequestPermission("phone") }
            )

            PermissionCard(
                title = "Draw Over Other Apps",
                description = "For the floating listening indicator",
                isGranted = permsStatus["overlay"] == true,
                onClick = {
                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:${context.packageName}")
                    )
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun PermissionCard(
    title: String,
    description: String,
    isGranted: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                width = 1.dp,
                color = if (isGranted) AkvaBlue else AkvaBorder,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = AkvaCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isGranted) Icons.Default.CheckCircle else Icons.Default.Warning,
                        contentDescription = null,
                        tint = if (isGranted) AkvaGreen else AkvaGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        color = AkvaWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    color = AkvaMuted,
                    fontSize = 12.sp
                )
            }
            if (!isGranted) {
                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(containerColor = AkvaDeepBlue),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.border(1.dp, AkvaBlue, RoundedCornerShape(8.dp))
                ) {
                    Text("Grant", color = AkvaWhite)
                }
            } else {
                Text("Granted", color = AkvaBlue, fontWeight = FontWeight.Bold)
            }
        }
    }
}
