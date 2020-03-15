package com.android.damir.moviedb.ui.home.latest


import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.android.damir.moviedb.R
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.BaseFragment
import com.android.damir.moviedb.ui.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_latest.*


class LatestFragment : BaseFragment() {

    private lateinit var latestViewModel: LatestViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    override val layoutRes: Int = R.layout.fragment_latest

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupObservers()
        setSwipeToRefresh()
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupRecyclerView() {
        movieListAdapter = MovieListAdapter(this, progressBarController)
        recyclerView.adapter = movieListAdapter
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        }else{
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupObservers() {
        latestViewModel = ViewModelProvider(this).get(LatestViewModel::class.java)
        latestViewModel.latest.observe(viewLifecycleOwner, Observer{
            updateList(it)
            hideRefreshing()
        })
    }

    private fun setSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            latestViewModel.refreshPopular()
        }
    }

    private fun hideRefreshing(){
        swipeRefreshLayout.isRefreshing = false
    }

    private fun updateList(movies: PagedList<Movie>) {
        movieListAdapter.submitList(movies)
    }
}
