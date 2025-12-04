package com.shaurya.snowflake.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.shaurya.snowflake.data.local.PreferencesManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiRepository @Inject constructor(
    private val preferencesManager: PreferencesManager
) {
    private suspend fun getModel(): GenerativeModel? {
        val apiKey = preferencesManager.apiKey.first()
        return if (!apiKey.isNullOrEmpty()) {
            GenerativeModel(
                modelName = "gemini-1.5-flash",
                apiKey = apiKey
            )
        } else {
            null
        }
    }

    suspend fun sendMessage(message: String): String {
        return try {
            val model = getModel()
            if (model == null) {
                return "\u26a0\ufe0f Please add your Gemini API key in Settings first!"
            }
            
            val response = model.generateContent(message)
            response.text ?: "I couldn't generate a response. Please try again."
        } catch (e: Exception) {
            when {
                e.message?.contains("API key") == true -> 
                    "\u26a0\ufe0f Invalid API key. Please check your API key in Settings."
                e.message?.contains("quota") == true -> 
                    "\u26a0\ufe0f API quota exceeded. Please wait or check your Google AI Studio quota."
                else -> 
                    "Error: ${e.localizedMessage ?: "Unknown error occurred"}"
            }
        }
    }

    suspend fun sendMessageWithHistory(
        message: String,
        history: List<Pair<String, String>>
    ): String {
        return try {
            sendMessage(message)
        } catch (e: Exception) {
            "Error: ${e.localizedMessage ?: "Unknown error occurred"}"
        }
    }
}