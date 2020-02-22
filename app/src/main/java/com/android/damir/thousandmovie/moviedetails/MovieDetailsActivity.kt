package com.android.damir.thousandmovie.moviedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.damir.thousandmovie.R
import com.android.damir.thousandmovie.domain.entity.Movie
import com.android.damir.thousandmovie.extension.load
import kotlinx.android.synthetic.main.activity_movie_detail.*

const val MOVIE_ID_EXTRA = "movie_id"

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getLongExtra(MOVIE_ID_EXTRA, -1)
        val movieDetailsViewModelFactory = MovieDetailsViewModelFactory(movieId)
        movieDetailsViewModel =
            ViewModelProvider(this, movieDetailsViewModelFactory)
                .get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.getDetails().observe(this, Observer {
            it?.let{updateDetails(it)}
        })

    }

    private fun updateDetails(movie: Movie){
        supportActionBar?.title = movie.title
        backdropImg.load(movie.backdropPath)
        voteAverage.text = movie.voteAverage
        movieTitle.text = movie.title
        releaseDate.text = movie.releaseDate
        overview.text = movie.overview
    }
}
