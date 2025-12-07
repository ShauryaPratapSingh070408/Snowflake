package com.shaurya.snowflake.presentation.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaurya.snowflake.data.local.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val apiKey: StateFlow<String?> = preferencesManager.apiKey
        .catch { 
            emit(null) 
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun saveApiKey(key: String?) {
        viewModelScope.launch {
            try {
                _isSaving.value = true
                _saveSuccess.value = false
                _errorMessage.value = null

                if (key == null) {
                    _errorMessage.value = "API key cannot be null"
                    _isSaving.value = false
                    return@launch
                }
                
                val trimmedKey = key.trim()
                
                if (trimmedKey.isEmpty()) {
                    _errorMessage.value = "API key cannot be empty"
                    _isSaving.value = false
                    return@launch
                }
                
                if (trimmedKey.length < 20) {
                    _errorMessage.value = "API key appears to be too short"
                    _isSaving.value = false
                    return@launch
                }
                
                if (!trimmedKey.startsWith("AIza")) {
                    _errorMessage.value = "API key should start with 'AIza'"
                    _isSaving.value = false
                    return@launch
                }
                
                try {
                    preferencesManager.saveApiKey(trimmedKey)
                    _saveSuccess.value = true
                    _errorMessage.value = null
                } catch (saveError: Exception) {
                    _errorMessage.value = "Failed to save: ${saveError.localizedMessage}"
                    _saveSuccess.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
                _saveSuccess.value = false
            } finally {
                _isSaving.value = false
            }
        }
    }
    
    fun clearError() {
        try {
            _errorMessage.value = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}