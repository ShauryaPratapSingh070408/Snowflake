package com.shaurya.snowflake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.shaurya.snowflake.presentation.ui.chat.ChatScreen
import com.shaurya.snowflake.presentation.ui.settings.SettingsScreen
import com.shaurya.snowflake.presentation.ui.theme.SnowflakeTheme
import com.shaurya.snowflake.presentation.ui.vision.VisionScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnowflakeTheme {
                SnowflakeApp()
            }
        }
    }
}

@Composable
fun SnowflakeApp() {
    var currentScreen by remember { mutableStateOf(Screen.Chat) }
    var showSettings by remember { mutableStateOf(false) }

    if (showSettings) {
        SettingsScreen(
            onBackClick = { showSettings = false }
        )
    } else {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentScreen == Screen.Chat,
                        onClick = { currentScreen = Screen.Chat },
                        icon = { Icon(Icons.Default.Chat, "Chat") },
                        label = { Text("💬 Chat") }
                    )
                    NavigationBarItem(
                        selected = currentScreen == Screen.Vision,
                        onClick = { currentScreen = Screen.Vision },
                        icon = { Icon(Icons.Default.Visibility, "Vision") },
                        label = { Text("👁️ Vision") }
                    )
                }
            }
        ) { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                color = MaterialTheme.colorScheme.background
            ) {
                when (currentScreen) {
                    Screen.Chat -> ChatScreen(
                        onSettingsClick = { showSettings = true }
                    )
                    Screen.Vision -> VisionScreen(
                        onSettingsClick = { showSettings = true }
                    )
                }
            }
        }
    }
}

enum class Screen {
    Chat,
    Vision
}