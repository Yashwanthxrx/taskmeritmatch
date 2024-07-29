package com.example.meritmatch.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ApiService

object RetrofitClient {
    private const val BASE_URL = "http://127.0.0.1:5000"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}