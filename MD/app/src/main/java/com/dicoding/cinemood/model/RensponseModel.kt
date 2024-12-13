package com.dicoding.cinemood.model

data class ResponseModel(
    val predictedemotion: String,
    val recommendations: List<Movie>
)
