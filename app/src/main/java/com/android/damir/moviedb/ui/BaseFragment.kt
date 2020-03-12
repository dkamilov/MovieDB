package com.android.damir.moviedb.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.damir.moviedb.domain.entity.Movie
import com.android.damir.moviedb.ui.adapter.OnMovieItemClickListener
import com.android.damir.moviedb.ui.details.MovieDetailsActivity
import com.android.damir.moviedb.utils.ProgressBarController

abstract class BaseFragment : Fragment(), OnMovieItemClickListener{

    protected abstract val layoutRes: Int
    protected var progressBarController: ProgressBarController? = null

    override fun onAttach(context: Context) {
        if(context is ProgressBarController)
            progressBarController = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onDetach() {
        progressBarController = null
        super.onDetach()
    }

    override fun onMovieItemClicked(movie: Movie?) {
        val intent = MovieDetailsActivity().newIntent(requireContext(), movie?.id?.toLong())
        startActivity(intent)
    }
}