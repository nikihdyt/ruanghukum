package com.example.ruanghukum.repository

import com.example.ruanghukum.data.remote.api.ApiService

class AiChatRepository private constructor(private val apiService: ApiService) {

    suspend fun getAiMessage(message: String) = apiService.getAiMessage(message)

    companion object {
        @Volatile
        private var instance: AiChatRepository? = null
        fun getInstance(apiService: ApiService): AiChatRepository =
            instance ?: synchronized(this) {
                instance ?: AiChatRepository(apiService)
            }.also { instance = it }
    }
}