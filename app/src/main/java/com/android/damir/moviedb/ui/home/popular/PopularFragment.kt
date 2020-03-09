package com.android.damir.moviedb.ui.home.popular

import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.android.damir.moviedb.R
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.adapter.OnMovieItemClickListener
import com.android.damir.moviedb.ui.adapter.MovieListAdapter
import com.android.damir.moviedb.ui.BaseFragment
import com.android.damir.moviedb.ui.details.MovieDetailsActivity
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : BaseFragment(), OnMovieItemClickListener {

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var popularViewModel: PopularViewModel
    override val layoutRes: Int
        get() = R.layout.fragment_popular

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupObservers()
        setSwipeToRefresh()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onMovieItemClicked(movie: Movie?) {
        val intent = MovieDetailsActivity().newIntent(requireContext(), movie?.id?.toLong())
        startActivity(intent)
    }

    private fun setupRecyclerView(){
        movieListAdapter = MovieListAdapter(this)
        recyclerView.adapter = movieListAdapter
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        }else{
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupObservers(){
        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
        popularViewModel.popular.observe(viewLifecycleOwner, Observer{
            updateList(it)
            hideRefreshing()
        })
    }

    private fun setSwipeToRefresh(){
        swipeRefreshLayout.setOnRefreshListener {
            popularViewModel.refreshPopular()
        }
    }

    private fun hideRefreshing(){
        swipeRefreshLayout.isRefreshing = false
    }

    private fun updateList(movies: PagedList<Movie>){
        movieListAdapter.submitList(movies)
    }
}