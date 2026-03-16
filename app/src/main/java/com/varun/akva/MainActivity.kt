package com.varun.akva

import android.Manifest
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.varun.akva.services.AKVAAccessibilityService
import com.varun.akva.services.AKVABackgroundService
import com.varun.akva.ui.*
import com.varun.akva.ui.theme.*

class MainActivity : ComponentActivity() {

    private var accessibilityGranted by mutableStateOf(false)
    private var notificationGranted by mutableStateOf(false)
    private var overlayGranted by mutableStateOf(false)
    private var microphoneGranted by mutableStateOf(false)
    private var phoneStateGranted by mutableStateOf(false)

    private val micPermLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        microphoneGranted = it
    }
    private val phonePermLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        phoneStateGranted = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refreshPermissions()

        setContent {
            AKVATheme {
                val navController = rememberNavController()
                val currentRoute by navController.currentBackStackEntryAsState()

                data class NavItem(val route: String, val icon: ImageVector, val label: String)
                val navItems = listOf(
                    NavItem("home", Icons.Default.Home, "Home"),
                    NavItem("setup", Icons.Default.Build, "Setup"),
                    NavItem("voice", Icons.Default.PlayArrow, "Voice"),
                    NavItem("log", Icons.Default.List, "Log"),
                    NavItem("settings", Icons.Default.Settings, "Settings")
                )

                Scaffold(
                    containerColor = AkvaBackground,
                    bottomBar = {
                        NavigationBar(containerColor = AkvaSurface) {
                            navItems.forEach { item ->
                                NavigationBarItem(
                                    icon = { Icon(item.icon, item.label) },
                                    label = { Text(item.label) },
                                    selected = currentRoute?.destination?.route == item.route,
                                    onClick = {
                                        if (currentRoute?.destination?.route != item.route) {
                                            navController.navigate(item.route) {
                                                popUpTo("home") { saveState = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = AkvaBlueWave,
                                        selectedTextColor = AkvaBlueWave,
                                        unselectedIconColor = AkvaMuted,
                                        unselectedTextColor = AkvaMuted,
                                        indicatorColor = AkvaBlueWave.copy(alpha = 0.15f)
                                    )
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeScreen(
                                onSetupClick = { navController.navigate("setup") },
                                onTestVoice = { navController.navigate("voice") },
                                onMorningBrief = { navController.navigate("voice") },
                                onSettingsClick = { navController.navigate("settings") }
                            )
                        }
                        composable("setup") {
                            PermissionsScreen(
                                accessibilityGranted, notificationGranted,
                                overlayGranted, microphoneGranted, phoneStateGranted
                            )
                        }
                        composable("voice") { VoiceTestScreen() }
                        composable("log") { ActivityLogScreen() }
                        composable("settings") { SettingsScreen() }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshPermissions()
        requestMissingPermissions()
        startBackgroundService()
    }

    private fun refreshPermissions() {
        accessibilityGranted = isAccessibilityEnabled()
        notificationGranted = isNotificationListenerEnabled()
        overlayGranted = Settings.canDrawOverlays(this)
        microphoneGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        phoneStateGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestMissingPermissions() {
        if (!microphoneGranted) micPermLauncher.launch(Manifest.permission.RECORD_AUDIO)
        else if (!phoneStateGranted) phonePermLauncher.launch(Manifest.permission.READ_PHONE_STATE)
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                registerForActivityResult(ActivityResultContracts.RequestPermission()) {}.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun isAccessibilityEnabled(): Boolean {
        return try {
            val am = getSystemService(ACCESSIBILITY_SERVICE) as AccessibilityManager
            am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN)
                .any { it.resolveInfo.serviceInfo.packageName == packageName }
        } catch (_: Exception) { false }
    }

    private fun isNotificationListenerEnabled(): Boolean {
        return try {
            val flat = Settings.Secure.getString(contentResolver, "enabled_notification_listeners") ?: ""
            val cn = ComponentName(this, "com.varun.akva.services.AKVANotificationListener")
            flat.contains(cn.flattenToString())
        } catch (_: Exception) { false }
    }

    private fun startBackgroundService() {
        try {
            val intent = Intent(this, AKVABackgroundService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(intent)
            else startService(intent)
        } catch (_: Exception) {}
    }
}
