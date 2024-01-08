package com.example.ruanghukum.factory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ruanghukum.di.Injection
import com.example.ruanghukum.repository.AiChatRepository
import com.example.ruanghukum.repository.AuthRepository
import com.example.ruanghukum.repository.LocalAiChatRepository
import com.example.ruanghukum.repository.UserRepository
import com.example.ruanghukum.views.aiChat.AIChatViewModel
import com.example.ruanghukum.views.auth.login.LoginViewModel
import com.example.ruanghukum.views.auth.register.RegisterViewModel
import com.example.ruanghukum.views.profile.ProfileViewModel

class ViewModelFactory(
    private val authRepo: AuthRepository,
    private val userRepo: UserRepository,
    private val aiChatRepo: AiChatRepository,
    private val localAiChatRepo: LocalAiChatRepository,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AIChatViewModel::class.java) -> {
                AIChatViewModel(aiChatRepo, localAiChatRepo) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(userRepo) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(authRepo, userRepo) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(authRepo, userRepo) as T
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
                        Injection.provideAuthRepository(context),
                        Injection.provideUserRepository(context),
                        Injection.provideAiChatRepository(context),
                        Injection.provideLocalAiChatRepository(context.applicationContext as Application),
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}