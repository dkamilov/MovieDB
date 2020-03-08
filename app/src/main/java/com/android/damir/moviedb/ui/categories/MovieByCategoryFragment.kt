package com.android.damir.moviedb.ui.categories

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.android.damir.moviedb.R
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.BaseFragment
import com.android.damir.moviedb.ui.adapter.MovieByCategoryAdapter
import com.android.damir.moviedb.ui.adapter.OnMovieItemClickListener
import com.android.damir.moviedb.ui.details.MovieDetailsActivity
import com.android.damir.moviedb.utils.CATEGORY_ID_EXTRA
import com.android.damir.moviedb.utils.CATEGORY_NAME_EXTRA
import kotlinx.android.synthetic.main.fragment_movie_by_category.*
import timber.log.Timber

class MovieByCategoryFragment : BaseFragment(), OnMovieItemClickListener {

    private lateinit var movieListAdapter: MovieByCategoryAdapter
    private lateinit var movieByCategoryViewModel: MovieByCategoryViewModel

    override val layoutRes: Int = R.layout.fragment_movie_by_category

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupToolbar()
        setupRecyclerView()
        setupObservers()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onMovieItemClicked(movie: Movie) {
        val intent = MovieDetailsActivity().newIntent(requireContext(), movie.id.toLong())
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
        movieListAdapter = MovieByCategoryAdapter(this)
        recyclerView.adapter = movieListAdapter
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        }else{
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupObservers() {
        val id = arguments?.getLong(CATEGORY_ID_EXTRA)
        movieByCategoryViewModel = MovieByCategoryViewModel()
        movieByCategoryViewModel.getMoviesByCategory(id!!).observe(viewLifecycleOwner, Observer {
            updateList(it)
        })
    }

    private fun updateList(movies: List<Movie>) {
        movieListAdapter.setItems(movies)
    }
}