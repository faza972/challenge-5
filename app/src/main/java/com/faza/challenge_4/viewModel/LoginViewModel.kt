package com.faza.challenge_4.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.faza.challenge_4.api.ApiService
import com.faza.challenge_4.model.User
import com.faza.challenge_4.util.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class LoginViewModel (private val repository: User): ViewModel() {

}