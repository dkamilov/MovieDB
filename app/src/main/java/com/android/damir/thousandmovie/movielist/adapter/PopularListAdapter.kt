package com.android.damir.thousandmovie.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.damir.thousandmovie.R
import com.android.damir.thousandmovie.domain.entity.Movie

class PopularListAdapter(
    private val movieItemClickListener: MovieItemClickListener
): ListAdapter<Movie, MovieItemViewHolder>(MovieDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_item_movie, parent, false)
        return MovieItemViewHolder(view, movieItemClickListener)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}