package com.faza.challenge_4.model


import com.google.gson.annotations.SerializedName

data class ListMenu(
    @SerializedName("data")
    val `data`: List<ListData?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)