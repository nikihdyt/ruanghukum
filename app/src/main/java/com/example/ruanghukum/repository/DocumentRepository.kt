package com.example.ruanghukum.repository

import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.data.local.datastore.UserPreferences
import com.example.ruanghukum.data.remote.api.ApiService
import com.example.ruanghukum.data.remote.request.DocumentLoginRequest
import com.example.ruanghukum.data.remote.request.DocumentNotLoginRequest
import com.example.ruanghukum.data.remote.response.DocumentLoginResponse
import com.example.ruanghukum.data.remote.response.DocumentNotLoginResponse
import kotlinx.coroutines.flow.Flow

class DocumentRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreferences
) {

    suspend fun createDocumentNotLogin(userToken: String, category: String, request: DocumentNotLoginRequest): DocumentNotLoginResponse {
        return apiService.createDocumentNotLogin(userToken, category, request)
    }

    suspend fun createDocumentLogin(userToken: String, category: String, request: DocumentLoginRequest): DocumentLoginResponse {
        return apiService.createDocumentLogin(userToken, category, request)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    companion object {
        @Volatile
        private var instance: DocumentRepository? = null
        fun getInstance(apiService: ApiService, userPreference: UserPreferences): DocumentRepository =
            instance ?: synchronized(this) {
                instance ?: DocumentRepository(apiService, userPreference)
            }.also { instance = it }
    }
}