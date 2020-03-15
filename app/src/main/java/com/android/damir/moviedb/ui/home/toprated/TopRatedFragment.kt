package com.android.damir.moviedb.ui.home.toprated


import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.android.damir.moviedb.R
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.BaseFragment
import com.android.damir.moviedb.ui.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_toprated.*

/**
 * A simple [Fragment] subclass.
 */
class TopRatedFragment : BaseFragment() {

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var topRatedViewModel: TopRatedViewModel

    override val layoutRes: Int = R.layout.fragment_toprated

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

    private fun setupObservers(){
        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        topRatedViewModel.topRated.observe(viewLifecycleOwner, Observer{
            updateList(it)
            hideRefreshing()
        })
    }

    private fun setSwipeToRefresh(){
        swipeRefreshLayout.setOnRefreshListener {
            topRatedViewModel.refreshPopular()
        }
    }

    private fun hideRefreshing() {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun updateList(movies: PagedList<Movie>){
        movieListAdapter.submitList(movies)
    }
}
