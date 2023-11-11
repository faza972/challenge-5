package com.faza.challenge_4.viewModel

import androidx.lifecycle.ViewModel
import com.faza.challenge_4.model.User


class ProfileViewModel(user: User) : ViewModel() {
    private var username: String = ""
    private var email: String = ""
    private var phone: String = ""

    fun setUserData(username: String, email: String, phone: String) {
        this.username = username
        this.email = email
        this.phone = phone
    }

    fun getUserData(): Triple<String, String, String> {
        return Triple(username, email, phone)
    }
}
