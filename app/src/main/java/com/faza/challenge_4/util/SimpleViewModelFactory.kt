package com.faza.challenge_4.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object SimpleViewModelFactory {
    fun create(vm: ViewModel) = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = vm as T
    }
}

