package com.example.ruanghukum.repository

import com.example.ruanghukum.data.remote.api.ApiService

class BlogRepository private constructor(
    private val apiService: ApiService
) {
    suspend fun getBlog() = apiService.getBlog()

    companion object {
        @Volatile
        private var instance: BlogRepository? = null
        fun getInstance(apiService: ApiService): BlogRepository =
            instance ?: synchronized(this) {
                instance ?: BlogRepository(apiService)
            }.also { instance = it }
    }
}