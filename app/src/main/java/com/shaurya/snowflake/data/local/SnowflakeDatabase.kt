package com.shaurya.snowflake.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        // TODO: Add entities here
        // ConversationEntity::class,
        // VisionMemoryEntity::class,
        // etc.
    ],
    version = 1,
    exportSchema = false
)
abstract class SnowflakeDatabase : RoomDatabase() {
    // TODO: Add DAOs here
    // abstract fun conversationDao(): ConversationDao
}