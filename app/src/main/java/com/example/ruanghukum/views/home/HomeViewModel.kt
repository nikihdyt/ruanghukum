package com.example.ruanghukum.views.home

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.navigation.NavController
import com.example.ruanghukum.R
import com.example.ruanghukum.repository.BlogRepository
import com.example.ruanghukum.utils.NetworkResultState
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val blogRepo: BlogRepository) : ViewModel() {
    fun getBlog() = liveData(Dispatchers.IO) {
        emit(NetworkResultState.Loading)
        try {
            emit(
                NetworkResultState.Success(data = blogRepo.getBlog()))
        } catch (e: Exception) {
            emit(NetworkResultState.Error(error = e.message ?: "Error Occurred!"))
        }
    }

    fun navigateToArticleDetail(
        navController: NavController,
        title: String,
        content: String,
        image: String,) {
        val bundle = Bundle().apply {
            putString("extra_title", title)
            putString("extra_content", content)
            putString("extra_image", image)
        }
        navController.navigate(R.id.action_navigation_home_to_articleDetailFragment, bundle)
    }
}