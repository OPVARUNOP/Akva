package com.varun.akva

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.*
import com.varun.akva.ui.*
import com.varun.akva.ui.theme.AKVATheme
import com.varun.akva.services.AKVABackgroundService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as AKVAApplication
        startForegroundService(Intent(this, AKVABackgroundService::class.java))
        setContent {
            AKVATheme {
                val nav = rememberNavController()
                val hist by app.repository.getRecentHistory(10).collectAsStateWithLifecycle(emptyList())
                Scaffold(bottomBar = {
                    NavigationBar {
                        NavigationBarItem(icon = { Icon(Icons.Default.Home, null) }, label = { Text("H") }, selected = false, onClick = { nav.navigate("h") })
                        NavigationBarItem(icon = { Icon(Icons.Default.Settings, null) }, label = { Text("S") }, selected = false, onClick = { nav.navigate("s") })
                    }
                }) { p ->
                    NavHost(nav, "h", modifier = Modifier.padding(p)) {
                        composable("h") { HomeScreen(hist) }
                        composable("s") { SettingsScreen(app.settings) }
                    }
                }
            }
        }
    }
}
