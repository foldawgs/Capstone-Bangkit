package com.dicoding.cinemood

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.cinemood.model.Movie

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val recommendations = intent.getParcelableArrayListExtra<Movie>("recommendations")

        val tvRecommendations = findViewById<TextView>(R.id.tvRecommendations)
        if (recommendations != null && recommendations.isNotEmpty()) {
            val movieTitles = recommendations.joinToString("\n") { "- ${it.title}" }
            tvRecommendations.text = movieTitles
        } else {
            tvRecommendations.text = "Tidak ada rekomendasi film."
        }
    }
}
