package com.faza.challenge_4.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val image : Int,
    val username : String,
    val password : String,
    val email : String,
    val number : String
) : Parcelable
