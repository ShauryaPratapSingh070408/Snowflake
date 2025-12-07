package com.shaurya.snowflake.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.shaurya.snowflake.presentation.ui.chat.ChatScreen
import com.shaurya.snowflake.presentation.ui.theme.SnowflakeTheme
import org.junit.Rule
import org.junit.Test

class ChatScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun chatScreen_displaysWelcomeMessage_whenNoMessages() {
        // Given: Chat screen with no messages
        composeTestRule.setContent {
            SnowflakeTheme {
                ChatScreen(onSettingsClick = {})
            }
        }

        // Then: Welcome message displayed
        composeTestRule.onNodeWithText("Welcome to Snowflake AI").assertIsDisplayed()
        composeTestRule.onNodeWithText("Powered by Google Gemini").assertIsDisplayed()
    }

    @Test
    fun chatScreen_settingsButton_isClickable() {
        // Given: Chat screen
        var settingsClicked = false
        composeTestRule.setContent {
            SnowflakeTheme {
                ChatScreen(onSettingsClick = { settingsClicked = true })
            }
        }

        // When: Click settings button
        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        // Then: Callback triggered
        assert(settingsClicked)
    }

    @Test
    fun chatScreen_inputField_acceptsText() {
        // Given: Chat screen
        composeTestRule.setContent {
            SnowflakeTheme {
                ChatScreen(onSettingsClick = {})
            }
        }

        // When: Type in input field
        composeTestRule.onNodeWithText("Ask me anything...").performTextInput("Hello AI")

        // Then: Text appears in field
        composeTestRule.onNodeWithText("Hello AI").assertIsDisplayed()
    }

    @Test
    fun chatScreen_sendButton_isDisabled_whenInputEmpty() {
        // Given: Chat screen with empty input
        composeTestRule.setContent {
            SnowflakeTheme {
                ChatScreen(onSettingsClick = {})
            }
        }

        // Then: Send button disabled
        composeTestRule.onNodeWithContentDescription("Send").assertIsNotEnabled()
    }

    @Test
    fun chatScreen_sendButton_isEnabled_whenInputHasText() {
        // Given: Chat screen
        composeTestRule.setContent {
            SnowflakeTheme {
                ChatScreen(onSettingsClick = {})
            }
        }

        // When: Enter text
        composeTestRule.onNodeWithText("Ask me anything...").performTextInput("Hello")

        // Then: Send button enabled
        composeTestRule.onNodeWithContentDescription("Send").assertIsEnabled()
    }
}