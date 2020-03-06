package com.android.damir.moviedb.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.damir.moviedb.domain.entity.Movie

object MovieDiffUtil : DiffUtil.ItemCallback<Movie>(){

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}