package com.shaurya.snowflake.presentation.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaurya.snowflake.data.repository.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val geminiRepository: GeminiRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening.asStateFlow()

    fun updateInputText(text: String) {
        _inputText.value = text
    }

    fun sendMessage() {
        val text = _inputText.value.trim()
        if (text.isBlank() || _isLoading.value) return

        viewModelScope.launch {
            try {
                // Add user message
                val userMessage = ChatMessage(
                    content = text,
                    isFromUser = true
                )
                _messages.value = _messages.value + userMessage
                _inputText.value = ""
                _isLoading.value = true

                // Get AI response from Gemini
                val response = geminiRepository.sendMessage(text)
                val aiMessage = ChatMessage(
                    content = response,
                    isFromUser = false
                )
                _messages.value = _messages.value + aiMessage
            } catch (e: Exception) {
                val errorMessage = ChatMessage(
                    content = "⚠️ Sorry, an error occurred: ${e.message ?: "Unknown error"}. Please try again.",
                    isFromUser = false
                )
                _messages.value = _messages.value + errorMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleListening() {
        // Voice feature coming in Phase 1.1
        _isListening.value = !_isListening.value
    }
}