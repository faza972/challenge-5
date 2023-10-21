package com.faza.challenge_4.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.faza.challenge_4.api.ApiService
import okhttp3.Dispatcher

class LoginViewModel (private val apiService: ApiService): ViewModel(){
    fun getAllCategory() = liveData(Dispatcher.IO)
}