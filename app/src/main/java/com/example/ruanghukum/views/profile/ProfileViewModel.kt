package com.example.ruanghukum.views.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepo: UserRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return userRepo.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            userRepo.logout()
        }
    }
}