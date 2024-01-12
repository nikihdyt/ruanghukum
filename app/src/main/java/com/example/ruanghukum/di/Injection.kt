package com.example.ruanghukum.di

import android.app.Application
import android.content.Context
import com.example.ruanghukum.data.local.datastore.UserPreferences
import com.example.ruanghukum.data.local.datastore.dataStore
import com.example.ruanghukum.data.remote.api.ApiConfig
import com.example.ruanghukum.repository.AiChatRepository
import com.example.ruanghukum.repository.AuthRepository
import com.example.ruanghukum.repository.DocumentRepository
import com.example.ruanghukum.repository.LocalAiChatRepository
import com.example.ruanghukum.repository.UserRepository

object Injection {

    fun provideAuthRepository(context: Context): AuthRepository {
        val apiService = ApiConfig.getApiService(context)
        return AuthRepository.getInstance(apiService)
    }

    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreferences.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }

    fun provideAiChatRepository(context: Context): AiChatRepository {
        val apiService = ApiConfig.getApiService(context)
        return AiChatRepository.getInstance(apiService)
    }

    fun provideLocalAiChatRepository(application: Application): LocalAiChatRepository {
        return LocalAiChatRepository(application)
    }

    fun provideDocRepository(context: Context): DocumentRepository {
        val apiService = ApiConfig.getApiService(context)
        return DocumentRepository.getInstance(apiService)
    }
}