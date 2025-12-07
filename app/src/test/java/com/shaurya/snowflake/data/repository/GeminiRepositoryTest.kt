package com.shaurya.snowflake.data.repository

import com.shaurya.snowflake.data.local.PreferencesManager
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GeminiRepositoryTest {

    @Mock
    private lateinit var preferencesManager: PreferencesManager

    private lateinit var geminiRepository: GeminiRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        geminiRepository = GeminiRepository(preferencesManager)
    }

    @Test
    fun `sendMessage without API key returns warning message`() = runTest {
        // Given: No API key
        `when`(preferencesManager.apiKey).thenReturn(flowOf(null))

        // When: Send message
        val result = geminiRepository.sendMessage("Hello")

        // Then: Warning message returned
        assertTrue(result.contains("Please add your Gemini API key"))
    }

    @Test
    fun `sendMessage with empty API key returns warning message`() = runTest {
        // Given: Empty API key
        `when`(preferencesManager.apiKey).thenReturn(flowOf(""))

        // When: Send message
        val result = geminiRepository.sendMessage("Hello")

        // Then: Warning message returned
        assertTrue(result.contains("Please add your Gemini API key"))
    }

    @Test
    fun `sendMessage with blank input returns response`() = runTest {
        // Given: Valid API key but blank message
        `when`(preferencesManager.apiKey).thenReturn(flowOf("AIza-test-key-123"))

        // When: Send blank message
        val result = geminiRepository.sendMessage("   ")

        // Then: Should attempt to process (API will handle validation)
        assertNotNull(result)
    }
}