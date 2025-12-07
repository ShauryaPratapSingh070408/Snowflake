package com.shaurya.snowflake.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.shaurya.snowflake.data.local.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiRepository @Inject constructor(
    private val preferencesManager: PreferencesManager
) {
    private suspend fun getModel(): GenerativeModel? {
        return withContext(Dispatchers.IO) {
            try {
                val apiKey = preferencesManager.apiKey.first()
                if (!apiKey.isNullOrEmpty() && apiKey.startsWith("AIza")) {
                    GenerativeModel(
                        modelName = "gemini-1.5-flash",
                        apiKey = apiKey
                    )
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun sendMessage(message: String): String = withContext(Dispatchers.IO) {
        try {
            if (message.trim().isEmpty()) {
                return@withContext "Please enter a message"
            }

            val model = getModel()
            if (model == null) {
                return@withContext "Please add your Gemini API key in Settings first!"
            }

            val limitedMessage = message.take(5000)
            
            val response = try {
                model.generateContent(limitedMessage)
            } catch (e: Exception) {
                return@withContext "API Error: ${e.localizedMessage ?: "Unknown error"}"
            }
            
            response.text?.trim() ?: "I couldn't generate a response. Please try again."
            
        } catch (e: Exception) {
            when {
                e.message?.contains("API key", ignoreCase = true) == true -> 
                    "Invalid API key. Please check your API key in Settings."
                e.message?.contains("quota", ignoreCase = true) == true -> 
                    "API quota exceeded. Please wait or check your Google AI Studio quota."
                e.message?.contains("network", ignoreCase = true) == true ->
                    "Network error. Please check your internet connection."
                e.message?.contains("connect", ignoreCase = true) == true ->
                    "Connection error. Please check your internet."
                e.message?.contains("SAFETY", ignoreCase = true) == true ->
                    "Content blocked by safety filters. Try rephrasing your question."
                e.message?.contains("timeout", ignoreCase = true) == true ->
                    "Request timed out. Please try again."
                else -> 
                    "Error: ${e.localizedMessage ?: "Unknown error occurred. Please try again."}"
            }
        }
    }

    suspend fun sendMessageWithHistory(
        message: String,
        history: List<Pair<String, String>>
    ): String = withContext(Dispatchers.IO) {
        try {
            sendMessage(message)
        } catch (e: Exception) {
            "Error: ${e.localizedMessage ?: "Unknown error occurred"}"
        }
    }
}