package com.dicoding.cinemood

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.cinemood.adapter.MovieAdapter
import com.dicoding.cinemood.model.Movie

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val predictedEmotion = intent.getStringExtra("predicted_emotion")
        val recommendations = intent.getParcelableArrayListExtra<Movie>("recommendations")


        val tvPredictedEmotion = findViewById<TextView>(R.id.tvPredictedEmotion)
        tvPredictedEmotion.text = "Predicted Emotion: $predictedEmotion"


        val rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
        rvMovies.layoutManager = LinearLayoutManager(this)
        if (recommendations != null && recommendations.isNotEmpty()) {
            val adapter = MovieAdapter(recommendations)
            rvMovies.adapter = adapter
        }
    }
}
