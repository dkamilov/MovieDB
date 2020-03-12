package com.android.damir.moviedb.ui.categories

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.android.damir.moviedb.R
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.BaseFragment
import com.android.damir.moviedb.ui.adapter.MovieListAdapter
import com.android.damir.moviedb.ui.adapter.OnMovieItemClickListener
import com.android.damir.moviedb.ui.details.MovieDetailsActivity
import com.android.damir.moviedb.utils.CATEGORY_ID_EXTRA
import com.android.damir.moviedb.utils.CATEGORY_NAME_EXTRA
import kotlinx.android.synthetic.main.fragment_movie_by_category.*

class MovieByCategoryFragment : BaseFragment(), OnMovieItemClickListener {

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieByCategoryViewModel: MovieByCategoryViewModel

    override val layoutRes: Int = R.layout.fragment_movie_by_category

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupToolbar()
        setupRecyclerView()
        setupSwipeToRefresh()
        setupObservers()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onMovieItemClicked(movie: Movie?) {
        val intent = MovieDetailsActivity().newIntent(requireContext(), movie?.id?.toLong())
        startActivity(intent)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = getToolbarTitle()
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun getToolbarTitle(): String? {
        return arguments?.getString(CATEGORY_NAME_EXTRA)
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

    private fun setupSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            movieByCategoryViewModel.refreshMovies()
        }
    }

    private fun setupObservers() {
        val id = arguments?.getLong(CATEGORY_ID_EXTRA)
        val factory = MovieByCategoryViewModelFactory(id!!)
        movieByCategoryViewModel = ViewModelProvider(this, factory).get(MovieByCategoryViewModel::class.java)
        movieByCategoryViewModel.movies.observe(viewLifecycleOwner, Observer {
            updateList(it)
            hideRefreshing()
        })
    }

    private fun updateList(movies: PagedList<Movie>) {
        movieListAdapter.submitList(movies)
    }

    private fun hideRefreshing() {
        swipeRefreshLayout.isRefreshing = false
    }
}