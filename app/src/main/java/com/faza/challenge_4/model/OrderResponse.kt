package com.faza.challenge_4.model


import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)