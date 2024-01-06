package com.example.ruanghukum.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.ruanghukum.data.local.room.aiChat.AiChat
import com.example.ruanghukum.data.local.room.aiChat.AiChatDao
import com.example.ruanghukum.data.local.room.aiChat.AiChatDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalAiChatRepository(application: Application) {

    private val mAiChatDao: AiChatDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = AiChatDatabase.getDatabase(application)
        mAiChatDao = db.aiChatDao()
    }

    fun insertToLocal(message: AiChat) {
        executorService.execute {
            mAiChatDao.insert(message)
        }
    }

    fun getMessagesFromLocal(): LiveData<List<AiChat>> = mAiChatDao.getMessages()
}