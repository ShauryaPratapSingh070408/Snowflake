package com.shaurya.snowflake.presentation.ui.vision

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaurya.snowflake.data.repository.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class VisionMode {
    LIVE,    // Continuous vision with voice commands
    PHOTO    // Capture and analyze photos
}

@HiltViewModel
class VisionViewModel @Inject constructor(
    private val geminiRepository: GeminiRepository
) : ViewModel() {

    private val _visionMode = MutableStateFlow(VisionMode.LIVE)
    val visionMode: StateFlow<VisionMode> = _visionMode.asStateFlow()

    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening.asStateFlow()

    private val _isProcessing = MutableStateFlow(false)
    val isProcessing: StateFlow<Boolean> = _isProcessing.asStateFlow()

    private val _lastResponse = MutableStateFlow("")
    val lastResponse: StateFlow<String> = _lastResponse.asStateFlow()

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    fun setVisionMode(mode: VisionMode) {
        _visionMode.value = mode
        _lastResponse.value = ""
    }

    fun toggleListening() {
        _isListening.value = !_isListening.value
        
        if (_isListening.value) {
            // TODO: Start Speech Recognition
            // When speech recognized, call analyzeWithVoiceCommand(recognizedText)
        } else {
            // TODO: Stop Speech Recognition
        }
    }

    fun analyzeWithVoiceCommand(command: String) {
        viewModelScope.launch {
            try {
                _isProcessing.value = true
                _lastResponse.value = ""
                
                // TODO: Capture current camera frame
                // TODO: Send frame + voice command to Gemini Vision
                
                val prompt = "Based on the image, answer this: $command"
                val response = geminiRepository.sendMessage(prompt)
                
                _lastResponse.value = response
                
                // TODO: Convert response to speech (TTS)
                speakResponse(response)
                
            } catch (e: Exception) {
                _lastResponse.value = "⚠️ Error: ${e.message}"
            } finally {
                _isProcessing.value = false
                _isListening.value = false
            }
        }
    }

    fun captureAndAnalyze() {
        viewModelScope.launch {
            try {
                _isProcessing.value = true
                _lastResponse.value = ""
                
                // TODO: Capture photo from camera
                // TODO: Send to Gemini Vision for analysis
                
                val response = geminiRepository.sendMessage("Describe this image in detail.")
                
                _lastResponse.value = response
                
                // TODO: Convert response to speech (TTS)
                speakResponse(response)
                
            } catch (e: Exception) {
                _lastResponse.value = "⚠️ Error: ${e.message}"
            } finally {
                _isProcessing.value = false
            }
        }
    }

    private fun speakResponse(text: String) {
        // TODO: Implement Text-to-Speech
        _isSpeaking.value = true
        // When done speaking: _isSpeaking.value = false
    }
}