package com.example.ruanghukum.data.local.room.aiChat

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AiChat::class], version = 1)
abstract class AiChatDatabase: RoomDatabase() {
    abstract fun aiChatDao(): AiChatDao

    companion object {
        @Volatile
        private var INSTANCE: AiChatDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): AiChatDatabase {
            if (INSTANCE == null) {
                synchronized(AiChatDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AiChatDatabase::class.java, "ai_chat_database")
                        .build()
                }
            }
            return INSTANCE as AiChatDatabase
        }
    }
}