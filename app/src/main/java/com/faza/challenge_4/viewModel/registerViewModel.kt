package com.faza.challenge_4.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faza.challenge_4.model.User

class registerViewModel (private val repository: User): ViewModel() {

    private val _registerResult = MutableLiveData <Boolean>()
    val registerResult: LiveData<Boolean>
        get() =_registerResult

}