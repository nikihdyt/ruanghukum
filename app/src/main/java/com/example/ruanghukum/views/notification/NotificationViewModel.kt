package com.example.ruanghukum.views.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.repository.DocumentRepository
import com.example.ruanghukum.repository.UserRepository
import com.example.ruanghukum.utils.NetworkResultState
import kotlinx.coroutines.Dispatchers

class NotificationViewModel(
    private val documentRepo: DocumentRepository,
    private val userRepo: UserRepository,
) : ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return userRepo.getSession().asLiveData()
    }

    fun getDocumentHistory(token: String) = liveData(Dispatchers.IO) {
        emit(NetworkResultState.Loading)
        try {
            emit(
                NetworkResultState.Success(data = documentRepo.getDocumentHistory(token)))
        } catch (e: Exception) {
            emit(NetworkResultState.Error(error = e.message ?: "Error Occurred!"))
        }
    }
}