package com.example.ruanghukum.repository

import com.example.ruanghukum.data.remote.api.ApiService

class AuthRepository private constructor(
    private val apiService: ApiService
) {
    suspend fun login(email: String, password: String) = apiService.login(
        email,
        password
    )

    suspend fun register(
        fullname: String,
        email: String,
        password: String,
        address: String,
        phoneNumber: String,
        gender: String,
        jobTitle: String,
        idCardNumber: String,
        birthDate: String,
    ) = apiService.register(
        fullname,
        email,
        password,
        address,
        phoneNumber,
        gender,
        jobTitle,
        idCardNumber,
        birthDate,
    )

    companion object {
        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(apiService: ApiService): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(apiService)
            }.also { instance = it }
    }
}