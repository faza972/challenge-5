package com.faza.challenge_4.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faza.challenge_4.api.ApiService
import com.faza.challenge_4.viewModel.LoginViewModel

class LoginViewModelFactory (val apiService: ApiService): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}

