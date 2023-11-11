package com.faza.challenge_4.api

import com.faza.challenge_4.model.User
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiInterface {
    @Headers("Content-type:application/json")
    @POST("auth_tokens")
    fun login(@Body info: User): retrofit2.Call<ResponseBody>

    @Headers("Content-type:application/json")
    @POST("users")
    fun register(@Body info: User): retrofit2.Call<ResponseBody>

    @GET("login")
    fun getAccessToken() : Call<String>
}

object ApiClient {
    const val BASE_URL = "https://671c4e06-ccad-4037-a84f-f6c2f13e087d.mock.pstmn.io"
    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }
}