package com.faza.challenge_4.api

import com.faza.challenge_4.model.CategoryMenu
import com.faza.challenge_4.model.OrderReq
import com.faza.challenge_4.model.OrderResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("category-menu")
    fun getCategory(): Call<CategoryMenu>
    @GET("listmenu")
    suspend fun getListMenu(): CategoryMenu
    @POST("order")
    fun postOrder(@Body orderReq: OrderReq): Call<OrderResponse>
}
