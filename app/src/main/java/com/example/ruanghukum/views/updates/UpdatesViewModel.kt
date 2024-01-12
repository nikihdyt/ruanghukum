package com.example.ruanghukum.views.updates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.ruanghukum.repository.BlogRepository
import com.example.ruanghukum.utils.NetworkResultState
import kotlinx.coroutines.Dispatchers

class UpdatesViewModel(private val blogRepo: BlogRepository) : ViewModel() {
    fun getBlog() = liveData(Dispatchers.IO) {
        emit(NetworkResultState.Loading)
        try {
            emit(
                NetworkResultState.Success(data = blogRepo.getBlog()))
        } catch (e: Exception) {
            emit(NetworkResultState.Error(error = e.message ?: "Error Occurred!"))
        }
    }
}