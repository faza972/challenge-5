package com.faza.challenge_4.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faza.challenge_4.model.Menu

class HomeViewModel: ViewModel() {
    val menuView = MutableLiveData<Boolean>().apply { value= true }
    val menuItem = MutableLiveData<ArrayList<Menu>>()
}