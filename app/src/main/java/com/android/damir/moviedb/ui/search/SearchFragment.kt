package com.android.damir.moviedb.ui.search

import android.content.res.Configuration
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.android.damir.moviedb.R
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.BaseFragment
import com.android.damir.moviedb.ui.adapter.MovieListAdapter
import com.android.damir.moviedb.utils.showKeyboard
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment() {

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var searchViewModel: SearchViewModel

    override val layoutRes: Int = R.layout.fragment_search

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        hideProgressVisibility()
        setupSearchView()
        setupToolbar()
        setupRecyclerView()
        setupViewModel()
        super.onActivityCreated(savedInstanceState)
    }

    private fun hideProgressVisibility() {
        progressBarController?.setProgressVisibility(false)
    }

    private fun setupSearchView() {
        search_edit_text.requestFocus()
        search_edit_text.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                searchMovie(v.text.toString())
            }
            false
        }
        search_edit_text.showKeyboard()
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private fun setupRecyclerView() {
        movieListAdapter = MovieListAdapter(this)
        recyclerView.adapter = movieListAdapter
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        }else{
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupViewModel() {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    private fun searchMovie(query: String) {
        searchViewModel.searchMovies(query).observe(viewLifecycleOwner, Observer{
            updateList(it)
        })
    }

    private fun updateList(movies: PagedList<Movie>) {
        movieListAdapter.submitList(movies)
    }
}