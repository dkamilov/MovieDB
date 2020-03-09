package com.android.damir.moviedb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.android.damir.moviedb.R
import com.android.damir.moviedb.domain.entity.Movie

class MovieByCategoryAdapter(
    private val listener: OnMovieItemClickListener
) : PagedListAdapter<Movie, MovieItemViewHolder>(MovieDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieItemViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }
}