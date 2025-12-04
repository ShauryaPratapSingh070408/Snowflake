package com.shaurya.snowflake

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SnowflakeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channels = listOf(
                NotificationChannel(
                    CHANNEL_OVERLAY,
                    "Snowflake Overlay",
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "Floating bubble overlay service"
                    setShowBadge(false)
                },
                NotificationChannel(
                    CHANNEL_FROST_VISION,
                    "Frost Vision",
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "Real-time visual AI processing"
                    setShowBadge(false)
                },
                NotificationChannel(
                    CHANNEL_VOICE,
                    "Voice Recognition",
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "Voice command processing"
                    setShowBadge(false)
                },
                NotificationChannel(
                    CHANNEL_ALERTS,
                    "Snowflake Alerts",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Important notifications from Snowflake"
                }
            )

            val manager = getSystemService(NotificationManager::class.java)
            channels.forEach { manager.createNotificationChannel(it) }
        }
    }

    companion object {
        const val CHANNEL_OVERLAY = "snowflake_overlay"
        const val CHANNEL_FROST_VISION = "frost_vision"
        const val CHANNEL_VOICE = "voice_recognition"
        const val CHANNEL_ALERTS = "snowflake_alerts"
    }
}