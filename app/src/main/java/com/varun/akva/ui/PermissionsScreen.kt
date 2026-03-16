package com.varun.akva.ui

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varun.akva.R
import com.varun.akva.ui.theme.*

data class PermissionItem(val name: String, val desc: String, val isGranted: Boolean, val onClick: () -> Unit)

@Composable
fun PermissionsScreen(
    accessibilityGranted: Boolean,
    notificationGranted: Boolean,
    overlayGranted: Boolean,
    microphoneGranted: Boolean,
    phoneStateGranted: Boolean
) {
    val context = LocalContext.current

    val permissions = listOf(
        PermissionItem("Accessibility Service", "Detects which apps you open and builds context for AKVA to speak.", accessibilityGranted) {
            context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        },
        PermissionItem("Notification Access", "Reads notification titles only (never content) to tell you who messaged.", notificationGranted) {
            context.startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        },
        PermissionItem("Display Over Other Apps", "Shows subtle screen mood overlays during certain times of day.", overlayGranted) {
            context.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        },
        PermissionItem("Microphone", "Listens for 'Hey AKVA' wake word and voice commands.", microphoneGranted) {},
        PermissionItem("Phone State", "Detects active calls to stay silent during phone calls.", phoneStateGranted) {}
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AkvaBackground)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))
        Image(
            painter = painterResource(id = R.drawable.akva_logo_vector),
            contentDescription = "AKVA Logo",
            modifier = Modifier.width(200.dp).height(85.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(24.dp))

        Text("Let AKVA Come Alive", color = AkvaWhite, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Grant these permissions to activate The Living OS.", color = AkvaMuted, fontSize = 14.sp)
        Spacer(Modifier.height(24.dp))

        val allGranted = permissions.all { it.isGranted }
        if (allGranted) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = AkvaGreen.copy(alpha = 0.15f))
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Check, null, tint = AkvaGreen, modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(12.dp))
                    Text("All permissions granted. AKVA is fully alive.", color = AkvaGreen, fontWeight = FontWeight.SemiBold)
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        permissions.forEachIndexed { idx, perm ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = AkvaSurface) // #12122A equivalent
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("${idx + 1}", color = AkvaBlueWave, fontSize = 20.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.size(36.dp).background(AkvaBlueWave.copy(alpha = 0.15f), CircleShape).wrapContentSize())
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Text(perm.name, color = AkvaWhite, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                        Text(perm.desc, color = AkvaMuted, fontSize = 12.sp, lineHeight = 16.sp)
                    }
                    Spacer(Modifier.width(8.dp))
                    if (perm.isGranted) {
                        Icon(Icons.Default.Check, null, tint = AkvaGreen, modifier = Modifier.size(28.dp))
                    } else {
                        if (idx < 3) {
                            TextButton(onClick = perm.onClick) { Text("Grant", color = AkvaAccentRed, fontWeight = FontWeight.Bold) }
                        } else {
                            Icon(Icons.Default.Close, null, tint = Color(0xFFFF1744), modifier = Modifier.size(28.dp))
                        }
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
        }
        Spacer(Modifier.height(32.dp))
    }
}
