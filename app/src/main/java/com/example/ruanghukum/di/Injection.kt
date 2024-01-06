package com.example.ruanghukum.di

import android.app.Application
import android.content.Context
import com.example.ruanghukum.data.remote.api.ApiConfig
import com.example.ruanghukum.repository.AiChatRepository
import com.example.ruanghukum.repository.LocalAiChatRepository

object Injection {

    fun provideAiChatRepository(context: Context): AiChatRepository {
        val apiService = ApiConfig.getApiService()
        return AiChatRepository.getInstance(apiService)
    }

    fun provideLocalAiChatRepository(application: Application): LocalAiChatRepository {
        return LocalAiChatRepository(application)
    }
}