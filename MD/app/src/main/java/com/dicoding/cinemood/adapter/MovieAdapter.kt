package com.dicoding.cinemood.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.cinemood.R
import com.dicoding.cinemood.model.Movie

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvMovieTitle)
        val tvPopularity: TextView = view.findViewById(R.id.tvPopularity)
        val btnImdb: Button = view.findViewById(R.id.btnImdb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.tvTitle.text = movie.title
        holder.tvPopularity.text = "Popularity: ${movie.popularity}"

        // Set up button click listener
        holder.btnImdb.setOnClickListener {
            val context = holder.itemView.context
            val imdbUrl = "https://www.imdb.com/find/?q=${movie.title.replace(" ", "+")}"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = android.net.Uri.parse(imdbUrl)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = movies.size
}