package com.example.ruanghukum.data.remote.api

import com.example.ruanghukum.data.remote.response.AiChatResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("api/chat")
    suspend fun getAiMessage(
        @Field("message") message: String
    ): AiChatResponse
}