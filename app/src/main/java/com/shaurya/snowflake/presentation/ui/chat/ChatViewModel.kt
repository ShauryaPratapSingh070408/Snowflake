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

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun updateInputText(text: String) {
        try {
            _inputText.value = text
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendMessage() {
        val text = _inputText.value.trim()
        
        if (text.isBlank()) {
            _errorMessage.value = "Please type a message first"
            return
        }
        
        if (_isLoading.value) {
            _errorMessage.value = "Please wait for the previous response"
            return
        }

        viewModelScope.launch {
            try {
                _errorMessage.value = null
                _isLoading.value = true

                val userMessage = ChatMessage(
                    content = text.take(5000),
                    isFromUser = true
                )
                
                val currentMessages = _messages.value.toMutableList()
                currentMessages.add(userMessage)
                _messages.value = currentMessages
                _inputText.value = ""

                val response = try {
                    geminiRepository.sendMessage(text)
                } catch (e: Exception) {
                    "Error: ${e.localizedMessage ?: "Unknown error"}"
                }

                val aiMessage = ChatMessage(
                    content = response.take(10000),
                    isFromUser = false
                )
                
                val updatedMessages = _messages.value.toMutableList()
                updatedMessages.add(aiMessage)
                _messages.value = updatedMessages
                
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Error: ${e.localizedMessage ?: "Unknown error"}"
                
                val errorMessage = ChatMessage(
                    content = "An error occurred: ${e.localizedMessage ?: "Unknown error"}",
                    isFromUser = false
                )
                
                try {
                    val updatedMessages = _messages.value.toMutableList()
                    updatedMessages.add(errorMessage)
                    _messages.value = updatedMessages
                } catch (e2: Exception) {
                    e2.printStackTrace()
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleListening() {
        try {
            _isListening.value = !_isListening.value
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}