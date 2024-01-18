package com.example.ruanghukum.views.documentPrep.documentPrepData

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.datastore.UserModel
import com.example.ruanghukum.data.remote.request.DocumentLoginRequest
import com.example.ruanghukum.data.remote.request.DocumentNotLoginRequest
import com.example.ruanghukum.data.remote.response.DocumentLoginResponse
import com.example.ruanghukum.data.remote.response.DocumentNotLoginResponse
import com.example.ruanghukum.repository.DocumentRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class DocumentPrepDataViewModel(private val docRepo: DocumentRepository) : ViewModel() {

    private val _documentStatus = MutableLiveData<Result<DocumentNotLoginResponse>>()
    val documentStatus: LiveData<Result<DocumentNotLoginResponse>> get() = _documentStatus

    private val _documentStatusLogin = MutableLiveData<Result<DocumentLoginResponse>>()
    val documentStatusLogin: LiveData<Result<DocumentLoginResponse>> get() = _documentStatusLogin

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun createDocumentPrep(userToken: String, category: String, documentRequest: DocumentNotLoginRequest) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val response = docRepo.createDocumentNotLogin(userToken, category, documentRequest)
                Log.d("DocumentResponse", "Full Response: $response")

                if (response.meta?.message == "Success") {
                    Log.d("DocumentResponse", "Success: ${response.meta.message}")
                    _documentStatus.postValue(Result.success(response))
                } else {
                    val errorBody = response.meta?.message ?: "Gagal membuat document"
                    Log.e("DocumentResponse", "Error: $errorBody")
                    _documentStatus.postValue(Result.failure(Exception(errorBody)))
                    _errorMessage.postValue(errorBody)
                }
            } catch (e: HttpException) {
                val errorBodyString = e.response()?.errorBody()?.string()
                try {
                    val jsonObject = JSONObject(errorBodyString)
                    val extractedMessage = jsonObject.getString("message")
                    Log.e("DocumentResponse", "Error: $extractedMessage")
                    _errorMessage.postValue(extractedMessage)
                } catch (ex: Exception) {
                    val errorMessage = "Terjadi kesalahan saat membuat document!"
                    Log.e("DocumentResponse", "Error: $errorMessage")
                    _errorMessage.postValue(errorMessage)
                }
            } catch (e: Exception) {
                Log.e("DocumentResponse", "Error: ${e.message}")
                _documentStatus.postValue(Result.failure(e))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun createDocumentPrepLogin(userToken: String, category: String, documentRequest: DocumentLoginRequest) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val response = docRepo.createDocumentLogin(userToken, category, documentRequest)
                Log.d("DocumentResponse", "Full Response: $response")

                if (response.meta?.message == "Success") {
                    Log.d("DocumentResponse", "Success: ${response.meta.message}")
                    _documentStatusLogin.postValue(Result.success(response))
                } else {
                    val errorBody = response.meta?.message ?: "Gagal membuat document"
                    Log.e("DocumentResponse", "Error: $errorBody")
                    _documentStatusLogin.postValue(Result.failure(Exception(errorBody)))
                    _errorMessage.postValue(errorBody)
                }
            } catch (e: HttpException) {
                val errorBodyString = e.response()?.errorBody()?.string()
                try {
                    val jsonObject = JSONObject(errorBodyString)
                    val extractedMessage = jsonObject.getString("message")
                    Log.e("DocumentResponse", "Error: $extractedMessage")
                    _errorMessage.postValue(extractedMessage)
                } catch (ex: Exception) {
                    val errorMessage = "Terjadi kesalahan saat membuat document!"
                    Log.e("DocumentResponse", "Error: $errorMessage")
                    _errorMessage.postValue(errorMessage)
                }
            } catch (e: Exception) {
                Log.e("DocumentResponse", "Error: ${e.message}")
                _documentStatus.postValue(Result.failure(e))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
    fun navigateToDocumentPrepPreview(navController: NavController, path: String) {
        val bundle = Bundle().apply {
            putString("documentPath", path)
        }
        navController.navigate(R.id.action_documentPrepData_to_documentPrepPreview, bundle)
    }

    fun getSession(): LiveData<UserModel> {
        return docRepo.getSession().asLiveData()
    }



}
