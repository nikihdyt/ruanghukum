package com.example.ruanghukum.repository

import com.example.ruanghukum.data.remote.api.ApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

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

    suspend fun updateProfile(
        token: String,
        profilePic: File,
        fullname: String,
        address: String,
        phoneNumber: String,
        gender: String,
        jobTitle: String,
        idCardNumber: String,
        birthDate: String,
        ) {
        val reqFullname = fullname.toRequestBody("text/plain".toMediaType())
        val reqAddress = address.toRequestBody("text/plain".toMediaType())
        val reqPhoneNumber = phoneNumber.toRequestBody("text/plain".toMediaType())
        val reqGender = gender.toRequestBody("text/plain".toMediaType())
        val reqJobTitle = jobTitle.toRequestBody("text/plain".toMediaType())
        val reqIdCardNumber = idCardNumber.toRequestBody("text/plain".toMediaType())
        val reqBirthDate = birthDate.toRequestBody("text/plain".toMediaType())
        val requestImageFile = profilePic.asRequestBody("image/jpeg".toMediaType())
        val multipartBodyImg = MultipartBody.Part.createFormData(
            "profilePicture",
            profilePic.name,
            requestImageFile
        )

        apiService.updateProfile(
            "Bearer $token",
            multipartBodyImg,
            reqFullname,
            reqAddress,
            reqPhoneNumber,
            reqGender,
            reqJobTitle,
            reqIdCardNumber,
            reqBirthDate,
        )
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(apiService: ApiService): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(apiService)
            }.also { instance = it }
    }
}