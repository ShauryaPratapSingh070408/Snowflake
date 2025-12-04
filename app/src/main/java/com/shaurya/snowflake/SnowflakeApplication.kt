package com.shaurya.snowflake

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SnowflakeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Application initialization
    }
}