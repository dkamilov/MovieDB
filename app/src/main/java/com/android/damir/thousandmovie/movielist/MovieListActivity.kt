package com.android.damir.thousandmovie.movielist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.damir.thousandmovie.R
import com.android.damir.thousandmovie.domain.entity.Movie
import com.android.damir.thousandmovie.moviedetails.MOVIE_ID_EXTRA
import com.android.damir.thousandmovie.moviedetails.MovieDetailsActivity
import com.android.damir.thousandmovie.movielist.adapter.MovieItemClickListener
import com.android.damir.thousandmovie.movielist.adapter.PopularListAdapter
import kotlinx.android.synthetic.main.activity_popular_list.*

class MovieListActivity : AppCompatActivity(), MovieItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var popularListAdapter: PopularListAdapter
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

    override fun onRefresh() {
        movieListViewModel.refreshPopular()
    }

    private fun setupRecyclerView(){
        popularListAdapter = PopularListAdapter(this)
        recyclerView.adapter = popularListAdapter
    }

    private fun setupObservers(){
        movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        movieListViewModel.popularListLiveData.observe(this, Observer{
            it?.let{updateList(it)}
        })
        movieListViewModel.isRefreshingLiveData.observe(this, Observer{ refreshing ->
            if(!refreshing) hideRefreshing()
        })
    }

    private fun setSwipeToRefresh(){
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    private fun hideRefreshing(){
        swipeRefreshLayout.isRefreshing = false
    }

    private fun updateList(movies: List<Movie>){
        popularListAdapter.submitList(movies)
    }
}
