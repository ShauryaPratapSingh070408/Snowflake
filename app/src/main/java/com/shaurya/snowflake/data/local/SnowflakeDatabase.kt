package com.shaurya.snowflake.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaurya.snowflake.data.local.dao.MessageDao
import com.shaurya.snowflake.data.local.entity.MessageEntity

/**
 * Room Database for Snowflake AI
 * Stores conversations, vision memories, and user preferences
 */
@Database(
    entities = [
        MessageEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SnowflakeDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}