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

        val recommendations = intent.getParcelableArrayListExtra<Movie>("recommendations") ?: emptyList()

        val rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.adapter = MovieAdapter(recommendations)
    }
}
