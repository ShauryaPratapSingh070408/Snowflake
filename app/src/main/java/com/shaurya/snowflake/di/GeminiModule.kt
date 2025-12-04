package com.shaurya.snowflake.di

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerationConfig
import com.shaurya.snowflake.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GeminiModule {

    @Provides
    @Singleton
    fun provideGenerativeModel(): GenerativeModel {
        val config = GenerationConfig.builder().apply {
            temperature = 0.7f
            topK = 40
            topP = 0.95f
            maxOutputTokens = 2048
        }.build()

        return GenerativeModel(
            modelName = "gemini-2.0-flash-exp",
            apiKey = BuildConfig.GEMINI_API_KEY,
            generationConfig = config
        )
    }
}