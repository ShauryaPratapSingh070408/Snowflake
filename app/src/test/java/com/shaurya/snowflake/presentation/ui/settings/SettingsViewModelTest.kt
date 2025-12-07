package com.shaurya.snowflake.presentation.ui.settings

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shaurya.snowflake.data.local.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SettingsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var preferencesManager: PreferencesManager

    private lateinit var viewModel: SettingsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        `when`(preferencesManager.apiKey).thenReturn(flowOf(null))
        viewModel = SettingsViewModel(preferencesManager)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `saveApiKey with empty key shows error`() = runTest {
        // When: Save empty key
        viewModel.saveApiKey("")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Error message shown
        assertEquals("API key cannot be empty", viewModel.errorMessage.value)
    }

    @Test
    fun `saveApiKey with invalid prefix shows error`() = runTest {
        // When: Save key without AIza prefix
        viewModel.saveApiKey("invalid-key-123")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Error message shown
        assertEquals("API key should start with 'AIza'", viewModel.errorMessage.value)
    }

    @Test
    fun `saveApiKey with valid key succeeds`() = runTest {
        // Given: Valid API key
        val validKey = "AIza-valid-key-12345"
        doNothing().`when`(preferencesManager).saveApiKey(validKey)

        // When: Save key
        viewModel.saveApiKey(validKey)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Success state true
        assertTrue(viewModel.saveSuccess.value)
        assertNull(viewModel.errorMessage.value)
    }

    @Test
    fun `saveApiKey trims whitespace`() = runTest {
        // Given: Key with whitespace
        val keyWithSpaces = "  AIza-valid-key  "
        val trimmedKey = "AIza-valid-key"
        doNothing().`when`(preferencesManager).saveApiKey(trimmedKey)

        // When: Save key
        viewModel.saveApiKey(keyWithSpaces)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Trimmed version saved
        verify(preferencesManager).saveApiKey(trimmedKey)
    }

    @Test
    fun `saveApiKey sets loading state`() = runTest {
        // Given: Valid key
        val validKey = "AIza-test-key"
        doNothing().`when`(preferencesManager).saveApiKey(validKey)

        // When: Start saving
        viewModel.saveApiKey(validKey)

        // Then: Loading true initially
        assertTrue(viewModel.isSaving.value)

        // Wait for completion
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Loading false after
        assertFalse(viewModel.isSaving.value)
    }

    @Test
    fun `saveApiKey handles repository error`() = runTest {
        // Given: Repository throws error
        val validKey = "AIza-test-key"
        `when`(preferencesManager.saveApiKey(validKey))
            .thenThrow(RuntimeException("Save failed"))

        // When: Save key
        viewModel.saveApiKey(validKey)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Error message shown
        assertNotNull(viewModel.errorMessage.value)
        assertFalse(viewModel.saveSuccess.value)
    }
}