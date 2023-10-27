package com.faza.challenge_4.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.faza.challenge_4.api.ApiService
import com.faza.challenge_4.util.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class LoginViewModel (private val apiService: ApiService): ViewModel() {
    fun getAllCategory() = liveData(Dispatchers.IO) {
        try {
            emit(Resource.success(data = apiService.getCategory()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}