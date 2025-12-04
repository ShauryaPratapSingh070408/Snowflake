package com.shaurya.snowflake.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerationConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiRepository @Inject constructor(
    private val generativeModel: GenerativeModel
) {
    suspend fun sendMessage(message: String): String {
        return try {
            val response = generativeModel.generateContent(message)
            response.text ?: "I couldn't generate a response. Please try again."
        } catch (e: Exception) {
            "Error: ${e.localizedMessage ?: "Unknown error occurred"}"
        }
    }

    suspend fun sendMessageWithHistory(
        message: String,
        history: List<Pair<String, String>>
    ): String {
        return try {
            // TODO: Implement conversation history support
            sendMessage(message)
        } catch (e: Exception) {
            "Error: ${e.localizedMessage ?: "Unknown error occurred"}"
        }
    }
}