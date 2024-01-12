package com.example.ruanghukum.data.remote.api

import com.example.ruanghukum.data.remote.response.AiChatResponse
import com.example.ruanghukum.data.remote.response.GetAllBlogResponse
import com.example.ruanghukum.data.remote.response.LoginResponse
import com.example.ruanghukum.data.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("fullname") fullname: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("gender") gender: String,
        @Field("jobTitle") jobTitle: String,
        @Field("idCardNumber") idCardNumber: String,
        @Field("birthDate") birthDate: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("chat")
    suspend fun getAiMessage(
        @Field("message") message: String
    ): AiChatResponse

    @GET("blog")
    suspend fun getBlog(): GetAllBlogResponse
}