package com.example.ruanghukum.views.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.repository.AuthRepository
import com.example.ruanghukum.repository.UserRepository
import com.example.ruanghukum.utils.NetworkResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepo: AuthRepository,
    private val userRepo: UserRepository,
): ViewModel() {

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepo.saveSession(user)
        }
    }

    fun login(
        email: String,
        password: String
    ) = liveData(Dispatchers.IO) {
        emit(NetworkResultState.Loading)
        try {
            emit(
                NetworkResultState.Success(data = authRepo.login(email, password)))
        } catch (e: Exception) {
            emit(NetworkResultState.Error(error = e.message ?: "Error Occurred!"))
        }
    }
}