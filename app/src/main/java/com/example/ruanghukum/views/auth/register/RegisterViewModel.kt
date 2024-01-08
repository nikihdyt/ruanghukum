package com.example.ruanghukum.views.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.repository.AuthRepository
import com.example.ruanghukum.repository.UserRepository
import com.example.ruanghukum.utils.NetworkResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepo: AuthRepository,
    private val userRepo: UserRepository,
) : ViewModel() {

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepo.saveSession(user)
        }
    }

    fun register(
        fullname: String,
        email: String,
        password: String,
        address: String,
        phoneNumber: String,
        gender: String,
        jobTitle: String,
        idCardNumber: String,
        birthDate: String,
    ) = liveData(Dispatchers.IO) {
        emit(NetworkResultState.Loading)
        try {
            emit(
                NetworkResultState.Success(data = authRepo.register(
                    fullname,
                    email,
                    password,
                    address,
                    phoneNumber,
                    gender,
                    jobTitle,
                    idCardNumber,
                    birthDate,
                )))
        } catch (e: Exception) {
            emit(NetworkResultState.Error(error = e.message ?: "Error Occurred!"))
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