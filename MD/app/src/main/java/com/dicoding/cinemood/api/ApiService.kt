package com.dicoding.cinemood.api

import com.dicoding.cinemood.model.RequestModel
import com.dicoding.cinemood.model.ResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("predict")
    fun getRecommendations(@Body request: RequestModel): Call<ResponseModel>
}