package com.android.damir.moviedb.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.utils.load
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieItemViewHolder(itemView: View, private val listener: OnMovieItemClickListener) : RecyclerView.ViewHolder(itemView){

    private val title = itemView.title
    private val backdropImg = itemView.backdropImg
    private val releaseDate = itemView.releaseDate
    private val voteAverage = itemView.voteAverage

    fun bind(item: Movie?){
        title.text = item?.title
        releaseDate.text = item?.releaseDate
        voteAverage.text = item?.voteAverage
        backdropImg.load(item?.backdropPath)
        itemView.setOnClickListener {
            listener.onMovieItemClicked(item)
        }
    }
}

interface OnMovieItemClickListener{
    fun onMovieItemClicked(movie: Movie?)
}