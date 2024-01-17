package com.example.ruanghukum.repository

import com.example.ruanghukum.data.remote.api.ApiService
import com.example.ruanghukum.data.remote.request.DocumentNotLoginRequest
import com.example.ruanghukum.data.remote.response.DocumentNotLoginResponse

class DocumentRepository private constructor(
    private val apiService: ApiService
) {

    suspend fun createDocumentNotLogin(category: String, request: DocumentNotLoginRequest): DocumentNotLoginResponse {
        return apiService.createDocumentNotLogin(category, request)
    }

    suspend fun getDocumentHistory(token: String) = apiService.getDocumentHistory("Bearer $token")

    companion object {
        @Volatile
        private var instance: DocumentRepository? = null
        fun getInstance(apiService: ApiService): DocumentRepository =
            instance ?: synchronized(this) {
                instance ?: DocumentRepository(apiService)
            }.also { instance = it }
    }
}