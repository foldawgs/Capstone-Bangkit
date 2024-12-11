package com.dicoding.cinemood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.cinemood.adapter.MovieAdapter
import com.dicoding.cinemood.model.Movie

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val recommendations = intent.getParcelableArrayListExtra<Movie>("recommendations")

        val rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
        rvMovies.layoutManager = LinearLayoutManager(this)
        if (recommendations != null && recommendations.isNotEmpty()) {
            val adapter = MovieAdapter(recommendations)
            rvMovies.adapter = adapter
        }
    }
}
