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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.*
import com.varun.akva.data.AkvaDatabase
import com.varun.akva.data.AkvaRepository
import com.varun.akva.data.SettingsManager
import com.varun.akva.services.AKVABackgroundService
import com.varun.akva.ui.*
import com.varun.akva.ui.theme.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var permsStatus by mutableStateOf(mapOf(
        "accessibility" to false,
        "notification" to false,
        "overlay" to false,
        "microphone" to false,
        "phone" to false
    ))

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        refreshPermissions()
    }

    private lateinit var repository: AkvaRepository
    private lateinit var settingsManager: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = AkvaDatabase.getDatabase(this)
        repository = AkvaRepository(
            database.usageDao(), 
            database.personalityDao(),
            database.userProfileDao(),
            database.conversationDao(),
            database.actionHistoryDao()
        )
        settingsManager = SettingsManager(this)

        refreshPermissions()

        setContent {
            AKVATheme {
                val navController = rememberNavController()

                Scaffold(
                    containerColor = AkvaBlack,
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination?.route
                        if (currentDestination != "splash" && currentDestination != "permissions") {
                            NavigationBar(containerColor = Color(0xFF00080D)) {
                                NavigationBarItem(
                                    selected = currentDestination == "home",
                                    onClick = { navController.navigate("home") },
                                    icon = { Icon(Icons.Default.Home, null) },
                                    label = { Text("HOME", fontFamily = FontFamily.Monospace, fontSize = 10.sp) },
                                    colors = NavigationBarItemDefaults.colors(selectedIconColor = AkvaBlue, unselectedIconColor = Color.Gray)
                                )
                                NavigationBarItem(
                                    selected = currentDestination == "memory",
                                    onClick = { navController.navigate("memory") },
                                    icon = { Icon(Icons.Default.Person, null) },
                                    label = { Text("MEMORY", fontFamily = FontFamily.Monospace, fontSize = 10.sp) },
                                    colors = NavigationBarItemDefaults.colors(selectedIconColor = AkvaBlue, unselectedIconColor = Color.Gray)
                                )
                                NavigationBarItem(
                                    selected = currentDestination == "actions",
                                    onClick = { navController.navigate("actions") },
                                    icon = { Icon(Icons.Default.Build, null) },
                                    label = { Text("ACTIONS", fontFamily = FontFamily.Monospace, fontSize = 10.sp) },
                                    colors = NavigationBarItemDefaults.colors(selectedIconColor = AkvaBlue, unselectedIconColor = Color.Gray)
                                )
                                NavigationBarItem(
                                    selected = currentDestination == "settings",
                                    onClick = { navController.navigate("settings") },
                                    icon = { Icon(Icons.Default.Settings, null) },
                                    label = { Text("CONFIG", fontFamily = FontFamily.Monospace, fontSize = 10.sp) },
                                    colors = NavigationBarItemDefaults.colors(selectedIconColor = AkvaBlue, unselectedIconColor = Color.Gray)
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "splash",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("splash") {
                            SplashScreen(onTimeout = {
                                navController.navigate("home") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            })
                        }
                        composable("home") {
                            val history by produceState(initialValue = emptyList<com.varun.akva.data.ConversationMemory>()) {
                                value = repository.getRecentConversations(10)
                            }
                            Column {
                                HomeScreen(
                                    onSetupClick = { navController.navigate("permissions") },
                                    onManualMicClick = { /* Handled by listening engine */ },
                                    onSettingsClick = { navController.navigate("settings") },
                                    onActivityLogClick = { navController.navigate("log") }
                                )
                                ConversationPanel(history = history, currentTranscript = "")
                            }
                        }
                        composable("memory") {
                            val scope = rememberCoroutineScope()
                            val profile by produceState(initialValue = null as com.varun.akva.data.UserProfile?) {
                                value = repository.getUserProfile()
                            }
                            MemoryScreen(profile = profile, onUpdate = {
                                scope.launch { repository.saveUserProfile(it) }
                            })
                        }
                        composable("actions") {
                            val actions by produceState(initialValue = emptyList<com.varun.akva.data.ActionHistory>()) {
                                value = repository.getRecentActions(20)
                            }
                            ActionsScreen(actions = actions)
                        }
                        composable("permissions") {
                            PermissionsScreen(
                                onBack = { navController.popBackStack() },
                                permsStatus = permsStatus,
                                onRequestPermission = { perm ->
                                    val androidPerm = when (perm) {
                                        "microphone" -> Manifest.permission.RECORD_AUDIO
                                        "phone" -> Manifest.permission.READ_PHONE_STATE
                                        else -> null
                                    }
                                    if (androidPerm != null) {
                                        permissionLauncher.launch(arrayOf(androidPerm))
                                    }
                                }
                            )
                        }
                        composable("settings") { 
                            SettingsScreen(onBack = { navController.popBackStack() }, settingsManager = settingsManager) 
                        }
                        composable("log") { 
                            ActivityLogScreen(onBack = { navController.popBackStack() }, repository = repository) 
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshPermissions()
        startBackgroundService()
    }

    private fun refreshPermissions() {
        permsStatus = mapOf(
            "accessibility" to isAccessibilityEnabled(),
            "notification" to isNotificationListenerEnabled(),
            "overlay" to Settings.canDrawOverlays(this),
            "microphone" to (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED),
            "phone" to (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
        )
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
