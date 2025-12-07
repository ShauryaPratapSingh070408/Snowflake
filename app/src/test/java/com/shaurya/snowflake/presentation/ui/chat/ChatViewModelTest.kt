package com.shaurya.snowflake.presentation.ui.chat

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shaurya.snowflake.data.repository.GeminiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class ChatViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var geminiRepository: GeminiRepository

    private lateinit var viewModel: ChatViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = ChatViewModel(geminiRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `updateInputText updates input state`() {
        // Given: Initial empty state
        assertEquals("", viewModel.inputText.value)

        // When: Update text
        viewModel.updateInputText("Hello AI")

        // Then: State updated
        assertEquals("Hello AI", viewModel.inputText.value)
    }

    @Test
    fun `sendMessage with blank text does nothing`() = runTest {
        // Given: Blank input
        viewModel.updateInputText("   ")

        // When: Send message
        viewModel.sendMessage()

        // Then: No messages added
        assertEquals(0, viewModel.messages.value.size)
    }

    @Test
    fun `sendMessage adds user message to list`() = runTest {
        // Given: Valid input
        viewModel.updateInputText("Hello")
        `when`(geminiRepository.sendMessage("Hello")).thenReturn("Hi there!")

        // When: Send message
        viewModel.sendMessage()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: User message added
        assertTrue(viewModel.messages.value.isNotEmpty())
        assertEquals("Hello", viewModel.messages.value.first().content)
        assertTrue(viewModel.messages.value.first().isFromUser)
    }

    @Test
    fun `sendMessage clears input after sending`() = runTest {
        // Given: Valid input
        viewModel.updateInputText("Hello")
        `when`(geminiRepository.sendMessage("Hello")).thenReturn("Hi!")

        // When: Send message
        viewModel.sendMessage()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Input cleared
        assertEquals("", viewModel.inputText.value)
    }

    @Test
    fun `sendMessage sets loading state`() = runTest {
        // Given: Valid input
        viewModel.updateInputText("Hello")
        `when`(geminiRepository.sendMessage("Hello")).thenReturn("Hi!")

        // When: Send message starts
        viewModel.sendMessage()

        // Then: Loading state is true
        assertTrue(viewModel.isLoading.value)

        // Wait for completion
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Loading state is false
        assertFalse(viewModel.isLoading.value)
    }

    @Test
    fun `sendMessage adds AI response after user message`() = runTest {
        // Given: Valid input and mock response
        viewModel.updateInputText("Hello")
        `when`(geminiRepository.sendMessage("Hello")).thenReturn("Hi there!")

        // When: Send message
        viewModel.sendMessage()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Both messages present
        assertEquals(2, viewModel.messages.value.size)
        assertTrue(viewModel.messages.value[0].isFromUser)
        assertFalse(viewModel.messages.value[1].isFromUser)
        assertEquals("Hi there!", viewModel.messages.value[1].content)
    }

    @Test
    fun `sendMessage handles error gracefully`() = runTest {
        // Given: Repository throws error
        viewModel.updateInputText("Hello")
        `when`(geminiRepository.sendMessage("Hello"))
            .thenThrow(RuntimeException("Network error"))

        // When: Send message
        viewModel.sendMessage()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Error message added
        assertTrue(viewModel.messages.value.any { it.content.contains("error") })
    }

    @Test
    fun `multiple messages maintain order`() = runTest {
        // Given: Multiple messages
        `when`(geminiRepository.sendMessage(anyString())).thenReturn("Response")

        // When: Send multiple messages
        viewModel.updateInputText("Message 1")
        viewModel.sendMessage()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.updateInputText("Message 2")
        viewModel.sendMessage()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then: Messages in correct order
        assertEquals(4, viewModel.messages.value.size) // 2 user + 2 AI
        assertEquals("Message 1", viewModel.messages.value[0].content)
        assertEquals("Message 2", viewModel.messages.value[2].content)
    }
}