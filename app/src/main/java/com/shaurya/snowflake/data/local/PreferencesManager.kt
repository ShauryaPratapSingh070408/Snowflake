package com.shaurya.snowflake.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "snowflake_prefs",
    corruptionHandler = ReplaceFileCorruptionHandler(
        produceNewData = { emptyPreferences() }
    )
)

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val GEMINI_API_KEY = stringPreferencesKey("gemini_api_key")

    val apiKey: Flow<String?> = context.dataStore.data
        .catch { exception ->
            // Handle any errors reading preferences
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[GEMINI_API_KEY]
        }

    suspend fun saveApiKey(apiKey: String) {
        try {
            context.dataStore.edit { preferences ->
                preferences[GEMINI_API_KEY] = apiKey
            }
        } catch (e: IOException) {
            // Handle write errors
            throw Exception("Failed to save API key: ${e.localizedMessage}")
        }
    }

    suspend fun clearApiKey() {
        try {
            context.dataStore.edit { preferences ->
                preferences.remove(GEMINI_API_KEY)
            }
        } catch (e: IOException) {
            throw Exception("Failed to clear API key: ${e.localizedMessage}")
        }
    }
}