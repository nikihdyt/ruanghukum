package com.example.ruanghukum.views.aiChat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.ruanghukum.data.local.room.aiChat.AiChat
import com.example.ruanghukum.repository.AiChatRepository
import com.example.ruanghukum.repository.LocalAiChatRepository
import com.example.ruanghukum.utils.NetworkResultState
import kotlinx.coroutines.Dispatchers

class AIChatViewModel(
    private val aiChatRepo: AiChatRepository,
    private val localAiChatRepo: LocalAiChatRepository,
    ) : ViewModel() {

    private val _messages = MutableLiveData<List<AiChat>>()
    val messages: LiveData<List<AiChat>> get() = _messages

    init {
        getMessages()
    }

    private fun getMessages() {
        localAiChatRepo.getMessagesFromLocal().observeForever {
            _messages.value = it
        }
    }

    fun sendMessage(message: AiChat) = liveData(Dispatchers.IO) {
        emit(NetworkResultState.Loading)
        try {
            emit(
                NetworkResultState.Success(data = aiChatRepo.getAiMessage(message.message!!)))
        } catch (e: Exception) {
            emit(NetworkResultState.Error(error = e.message ?: "Error Occurred!"))
        }
    }

    fun insertToLocal(aiChat: AiChat) {
        localAiChatRepo.insertToLocal(aiChat)
    }
}