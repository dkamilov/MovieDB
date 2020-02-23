package com.android.damir.thousandmovie.movielist

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.damir.thousandmovie.R
import com.android.damir.thousandmovie.domain.entity.Movie
import com.android.damir.thousandmovie.moviedetails.MOVIE_ID_EXTRA
import com.android.damir.thousandmovie.moviedetails.MovieDetailsActivity
import com.android.damir.thousandmovie.movielist.adapter.MovieItemClickListener
import com.android.damir.thousandmovie.movielist.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.activity_popular_list.*
import timber.log.Timber

class MovieListActivity : AppCompatActivity(),
    MovieItemClickListener {

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_list)

        setupRecyclerView()
        setupObservers()
        setSwipeToRefresh()
    }

    override fun movieItemClicked(movie: Movie) {
        val intent = Intent(this@MovieListActivity, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID_EXTRA, movie.id.toLong())
        startActivity(intent)
    }

    private fun setupRecyclerView(){
        movieListAdapter = MovieListAdapter(this)
        recyclerView.adapter = movieListAdapter
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(this, 3)
        }else{
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

    }

    private fun setupObservers(){
        movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        movieListViewModel.popularPagedListLiveData.observe(this, Observer{
            it?.let{updateList(it)}
        })
        movieListViewModel.isRefreshingLiveData.observe(this, Observer{ refreshing ->
            if(!refreshing) hideRefreshing()
        })
    }

    private fun setSwipeToRefresh(){
        swipeRefreshLayout.setOnRefreshListener {
            movieListViewModel.refreshPopular()
        }
    }

    private fun hideRefreshing(){
        swipeRefreshLayout.isRefreshing = false
    }

    private fun updateList(movies: PagedList<Movie>){
        Timber.i("updateList")
        movieListAdapter.submitList(movies)
    }
}
