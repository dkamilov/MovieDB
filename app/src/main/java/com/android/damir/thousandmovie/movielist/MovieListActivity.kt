package com.android.damir.thousandmovie.movielist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

class MovieListActivity : AppCompatActivity(), MovieItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieListViewModel: MovieListViewModel
    private var isLoading = false
    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_list)

        setupRecyclerView()
        setupObservers()
        setSwipeToRefresh()
        setupOnScrollListener()
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
        movieListAdapter = MovieListAdapter(this)
        recyclerView.adapter = movieListAdapter
    }

    private fun setupObservers(){
        movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        movieListViewModel.popularListLiveData.observe(this, Observer{
            it?.let{updateList(it)}
        })
        movieListViewModel.isRefreshingLiveData.observe(this, Observer{ refreshing ->
            if(!refreshing) hideRefreshing()
        })
        movieListViewModel.isLoadingLiveData.observe(this, Observer {
            isLoading = it
        })
        movieListViewModel.currentPageLiveData.observe(this, Observer{
            currentPage = it
        })
    }

    private fun setSwipeToRefresh(){
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    private fun hideRefreshing(){
        swipeRefreshLayout.isRefreshing = false
    }

    private fun updateList(movies: List<Movie>){
        movieListAdapter.submitList(movies)
    }

    private fun setupOnScrollListener() {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if(firstVisibleItem + visibleItemCount >= totalItemCount){
                    if(!isLoading){
                        //TODO GET Movies(currentPage + 1)
                        movieListViewModel.requestPopular(currentPage + 1)
                        isLoading = true
                    }
                }
            }
        })
    }
}
