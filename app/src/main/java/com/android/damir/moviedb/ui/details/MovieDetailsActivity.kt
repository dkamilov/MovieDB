package com.android.damir.moviedb.ui.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.damir.moviedb.R
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.utils.MOVIE_ID_EXTRA
import com.android.damir.moviedb.utils.load
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setupToolbar()
        setupObservers()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupObservers() {
        val movieId = intent.getLongExtra(MOVIE_ID_EXTRA, -1)
        val movieDetailsViewModelFactory = MovieDetailsViewModelFactory(movieId)
        movieDetailsViewModel = ViewModelProvider(this, movieDetailsViewModelFactory)
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

    fun newIntent(context: Context, movieId: Long?): Intent{
        val intent = Intent(context, this@MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID_EXTRA, movieId)
        return intent
    }
}
