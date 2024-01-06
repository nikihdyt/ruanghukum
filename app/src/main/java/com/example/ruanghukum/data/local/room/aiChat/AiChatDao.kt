package com.example.ruanghukum.data.local.room.aiChat

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AiChatDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(message: AiChat)

    @Query("SELECT * from aichat ORDER BY id DESC")
    fun getMessages(): LiveData<List<AiChat>>
}