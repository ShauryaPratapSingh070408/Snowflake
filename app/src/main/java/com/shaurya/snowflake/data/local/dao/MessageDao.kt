package com.shaurya.snowflake.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shaurya.snowflake.data.local.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for accessing messages from the database
 */
@Dao
interface MessageDao {
    @Query("SELECT * FROM messages ORDER BY timestamp DESC")
    fun getAllMessages(): Flow<List<MessageEntity>>
    
    @Insert
    suspend fun insertMessage(message: MessageEntity)
    
    @Query("DELETE FROM messages")
    suspend fun deleteAllMessages()
}