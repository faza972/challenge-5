package com.faza.challenge_4.model


import com.google.gson.annotations.SerializedName

data class CategoryMenu(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val `data`: List<CategoryData?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)