package com.example.ruanghukum.views.documentPrep.documentPrepData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.data.local.dummy.DocumentCategory
import com.example.ruanghukum.data.remote.request.DocumentNotLoginRequest
import com.example.ruanghukum.repository.AuthRepository
import com.example.ruanghukum.repository.DocumentRepository
import com.example.ruanghukum.repository.UserRepository
import com.example.ruanghukum.utils.NetworkResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DocumentPrepDataViewModel(
    private val docRepo: DocumentRepository
): ViewModel() {

    fun createDocument(category: String, documentRequest: DocumentNotLoginRequest) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val response = docRepo.createDocumentNotLogin(category, documentRequest)
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

}