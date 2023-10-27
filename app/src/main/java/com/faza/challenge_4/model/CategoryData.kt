package com.faza.challenge_4.model


import com.google.gson.annotations.SerializedName

data class CategoryData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("nama")
    val nama: String?
)