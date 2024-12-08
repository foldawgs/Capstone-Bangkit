package com.dicoding.cinemood.model

data class ResponseModel(
    val predicted_emotion: String,
    val recommendations: List<Movie>
)
