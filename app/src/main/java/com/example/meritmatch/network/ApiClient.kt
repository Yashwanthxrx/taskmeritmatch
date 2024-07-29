package com.example.meritmatch.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ApiService

object ApiClient {

    private const val BASE_URL = "http://127.0.0.1:5000"
    // Initialize Retrofit using lazy delegation to ensure it's only created once
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getClient(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}