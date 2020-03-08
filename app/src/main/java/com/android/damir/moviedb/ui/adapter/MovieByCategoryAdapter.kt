package com.android.damir.moviedb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.damir.moviedb.R
import com.android.damir.moviedb.domain.entity.Movie

class MovieByCategoryAdapter(
    private val listener: OnMovieItemClickListener
) : RecyclerView.Adapter<MovieItemViewHolder>() {

    private var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieItemViewHolder(view, listener)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    fun setItems(items: List<Movie>) {
        movies = items
        notifyDataSetChanged()
    }

}