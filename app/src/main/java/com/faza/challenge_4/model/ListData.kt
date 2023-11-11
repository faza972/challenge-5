package com.faza.challenge_4.model


import com.google.gson.annotations.SerializedName
import java.net.IDN

data class ListData(
    @SerializedName ("id")
    val id: Int?,
    @SerializedName("alamat_resto")
    val alamatResto: String?,
    @SerializedName("detail")
    val detail: String?,
    @SerializedName("harga")
    val harga: Int?,
    @SerializedName("harga_format")
    val hargaFormat: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("nama")
    val nama: String?
)