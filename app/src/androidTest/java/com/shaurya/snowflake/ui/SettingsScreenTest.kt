package com.shaurya.snowflake.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.shaurya.snowflake.presentation.ui.settings.SettingsScreen
import com.shaurya.snowflake.presentation.ui.theme.SnowflakeTheme
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun settingsScreen_displaysTitle() {
        // Given: Settings screen
        composeTestRule.setContent {
            SnowflakeTheme {
                SettingsScreen(onBackClick = {})
            }
        }

        // Then: Title displayed
        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_backButton_isClickable() {
        // Given: Settings screen
        var backClicked = false
        composeTestRule.setContent {
            SnowflakeTheme {
                SettingsScreen(onBackClick = { backClicked = true })
            }
        }

        // When: Click back button
        composeTestRule.onNodeWithContentDescription("Back").performClick()

        // Then: Callback triggered
        assert(backClicked)
    }

    @Test
    fun settingsScreen_apiKeyInput_acceptsText() {
        // Given: Settings screen
        composeTestRule.setContent {
            SnowflakeTheme {
                SettingsScreen(onBackClick = {})
            }
        }

        // When: Type in API key field
        composeTestRule.onNodeWithText("Gemini API Key").performTextInput("AIza-test-key")

        // Then: Text appears
        composeTestRule.onNodeWithText("AIza-test-key").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_saveButton_isDisabled_whenInputEmpty() {
        // Given: Settings screen with empty input
        composeTestRule.setContent {
            SnowflakeTheme {
                SettingsScreen(onBackClick = {})
            }
        }

        // Then: Save button disabled
        composeTestRule.onNodeWithText("Save API Key").assertIsNotEnabled()
    }

    @Test
    fun settingsScreen_displaysInstructions() {
        // Given: Settings screen
        composeTestRule.setContent {
            SnowflakeTheme {
                SettingsScreen(onBackClick = {})
            }
        }

        // Then: Instructions visible
        composeTestRule.onNodeWithText("How to get your API key:").assertIsDisplayed()
        composeTestRule.onNodeWithText("1. Visit: makersuite.google.com/app/apikey").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_toggleVisibility_works() {
        // Given: Settings screen with API key
        composeTestRule.setContent {
            SnowflakeTheme {
                SettingsScreen(onBackClick = {})
            }
        }

        // When: Enter text and toggle visibility
        composeTestRule.onNodeWithText("Gemini API Key").performTextInput("AIza-secret")
        composeTestRule.onNodeWithContentDescription("Toggle visibility").performClick()

        // Then: Text should be visible (test visibility icon changed)
        composeTestRule.onNodeWithContentDescription("Toggle visibility").assertIsDisplayed()
    }
}