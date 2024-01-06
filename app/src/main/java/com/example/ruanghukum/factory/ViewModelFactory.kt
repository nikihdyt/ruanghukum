package com.example.ruanghukum.factory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ruanghukum.di.Injection
import com.example.ruanghukum.repository.AiChatRepository
import com.example.ruanghukum.repository.LocalAiChatRepository
import com.example.ruanghukum.views.aiChat.AIChatViewModel

class ViewModelFactory(
    private val aiChatRepo: AiChatRepository,
    private val localAiChatRepo: LocalAiChatRepository,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AIChatViewModel::class.java) -> {
                AIChatViewModel(aiChatRepo, localAiChatRepo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(
                        Injection.provideAiChatRepository(context),
                        Injection.provideLocalAiChatRepository(context.applicationContext as Application),
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}