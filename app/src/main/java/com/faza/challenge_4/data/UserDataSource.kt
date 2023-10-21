package com.faza.challenge_4.data

import com.faza.challenge_4.R
import com.faza.challenge_4.menu.User

interface UserDataSource {
    fun getUserData(): User
}

class UserDataSourceImpl(): UserDataSource {
    override fun getUserData(): User =
        User(
            image = R.drawable.minum,
            username = "Faza",
            password = "Faza7997",
            email = "fazaqila079@gmail.com",
            number = "082385380872"
        )
}