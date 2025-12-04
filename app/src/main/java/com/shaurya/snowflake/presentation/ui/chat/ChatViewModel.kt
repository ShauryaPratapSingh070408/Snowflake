package com.shaurya.snowflake.presentation.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaurya.snowflake.data.repository.GeminiRepository
import com.shaurya.snowflake.service.voice.VoiceRecognitionService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val geminiRepository: GeminiRepository,
    private val voiceService: VoiceRecognitionService
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening.asStateFlow()

    init {
        // Listen for wake word detection
        viewModelScope.launch {
            voiceService.recognizedText.collect { text ->
                if (text.isNotBlank()) {
                    if (text.contains("hey snowflake", ignoreCase = true)) {
                        // Wake word detected - start listening for command
                        handleWakeWordDetected()
                    } else if (_isListening.value) {
                        // Process voice command
                        _inputText.value = text
                        sendMessage()
                        stopListening()
                    }
                }
            }
        }
    }

    fun updateInputText(text: String) {
        _inputText.value = text
    }

    fun sendMessage() {
        val text = _inputText.value.trim()
        if (text.isBlank() || _isLoading.value) return

        viewModelScope.launch {
            // Add user message
            val userMessage = ChatMessage(
                content = text,
                isFromUser = true
            )
            _messages.value = _messages.value + userMessage
            _inputText.value = ""
            _isLoading.value = true

            try {
                // Get AI response from Gemini
                val response = geminiRepository.sendMessage(text)
                val aiMessage = ChatMessage(
                    content = response,
                    isFromUser = false
                )
                _messages.value = _messages.value + aiMessage
            } catch (e: Exception) {
                val errorMessage = ChatMessage(
                    content = "Sorry, I encountered an error: ${e.message ?: "Unknown error"}",
                    isFromUser = false
                )
                _messages.value = _messages.value + errorMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleListening() {
        if (_isListening.value) {
            stopListening()
        } else {
            startListening()
        }
    }

    private fun startListening() {
        _isListening.value = true
        voiceService.startListening()
    }

    private fun stopListening() {
        _isListening.value = false
        voiceService.stopListening()
    }

    private fun handleWakeWordDetected() {
        // Wake word detected - provide visual feedback
        viewModelScope.launch {
            val wakeMessage = ChatMessage(
                content = "👋 Yes, I'm listening!",
                isFromUser = false
            )
            _messages.value = _messages.value + wakeMessage
            startListening() // Start listening for actual command
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopListening()
    }
}