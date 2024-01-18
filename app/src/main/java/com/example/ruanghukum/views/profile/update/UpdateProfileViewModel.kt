package com.example.ruanghukum.views.profile.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.repository.AuthRepository
import com.example.ruanghukum.repository.UserRepository
import com.example.ruanghukum.utils.NetworkResultState
import kotlinx.coroutines.Dispatchers
import java.io.File

class UpdateProfileViewModel(
    private val userRepo: UserRepository,
    private val authRepo: AuthRepository,
) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return userRepo.getSession().asLiveData()
    }

    fun updateProfile(
        token: String,
        picture: File,
        fullname: String,
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
                NetworkResultState.Success(
                    data = authRepo.updateProfile(
                        token,
                        picture,
                        fullname,
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
}