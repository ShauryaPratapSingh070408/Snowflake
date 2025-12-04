package com.shaurya.snowflake.di

import android.content.Context
import androidx.room.Room
import com.shaurya.snowflake.data.local.SnowflakeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSnowflakeDatabase(
        @ApplicationContext context: Context
    ): SnowflakeDatabase {
        return Room.databaseBuilder(
            context,
            SnowflakeDatabase::class.java,
            "snowflake_memory_db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }
}