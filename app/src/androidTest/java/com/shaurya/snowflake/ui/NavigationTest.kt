package com.shaurya.snowflake.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.shaurya.snowflake.SnowflakeApp
import com.shaurya.snowflake.presentation.ui.theme.SnowflakeTheme
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigation_switchBetweenTabs() {
        // Given: Main app
        composeTestRule.setContent {
            SnowflakeTheme {
                SnowflakeApp()
            }
        }

        // Then: Chat tab initially selected
        composeTestRule.onNodeWithText("💬 Chat").assertIsDisplayed()

        // When: Switch to Vision tab
        composeTestRule.onNodeWithText("👁️ Vision").performClick()

        // Then: Vision screen displayed
        composeTestRule.onNodeWithText("🐁️ Frost Vision").assertIsDisplayed()

        // When: Switch back to Chat
        composeTestRule.onNodeWithText("💬 Chat").performClick()

        // Then: Chat screen displayed
        composeTestRule.onNodeWithText("Snowflake AI").assertIsDisplayed()
    }

    @Test
    fun navigation_openSettings_fromChat() {
        // Given: Main app on Chat tab
        composeTestRule.setContent {
            SnowflakeTheme {
                SnowflakeApp()
            }
        }

        // When: Click settings from Chat
        composeTestRule.onAllNodesWithContentDescription("Settings").onFirst().performClick()

        // Then: Settings screen displayed
        composeTestRule.onNodeWithText("Gemini API Configuration").assertIsDisplayed()
    }

    @Test
    fun navigation_backFromSettings_toChat() {
        // Given: Settings screen open
        composeTestRule.setContent {
            SnowflakeTheme {
                SnowflakeApp()
            }
        }

        // Open settings
        composeTestRule.onAllNodesWithContentDescription("Settings").onFirst().performClick()

        // When: Click back
        composeTestRule.onNodeWithContentDescription("Back").performClick()

        // Then: Back to Chat
        composeTestRule.onNodeWithText("💬 Chat").assertIsDisplayed()
    }
}