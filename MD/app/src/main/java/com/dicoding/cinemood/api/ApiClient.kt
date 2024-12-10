package com.dicoding.cinemood.api

import android.util.Log
import com.dicoding.cinemood.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val BASE_URL: String = BuildConfig.BASE_URL
    init {
        Log.d("ApiClient", "BASE_URL: $BASE_URL")
    }

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
