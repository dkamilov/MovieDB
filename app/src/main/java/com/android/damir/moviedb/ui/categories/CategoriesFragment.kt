package com.android.damir.moviedb.ui.categories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.damir.moviedb.R
import com.android.damir.moviedb.data.api.Category
import com.android.damir.moviedb.ui.BaseFragment
import com.android.damir.moviedb.ui.adapter.CategoriesAdapter
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : BaseFragment() {

    //TODO: 1.Category item style
    //TODO: 2.Category item click
    //TODO: 3.Discover movie by category
    //TODO: 4.Query images of movie(without language query)

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var categoriesViewModel: CategoriesViewModel

    override val layoutRes: Int = R.layout.fragment_categories

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupToolbar()
        setupRecyclerView()
        setupObservers()
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.categories)
    }

    private fun setupRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        recyclerView.adapter = categoriesAdapter
    }

    private fun setupObservers() {
        categoriesViewModel = CategoriesViewModel()
        categoriesViewModel.getCategories().observe(viewLifecycleOwner, Observer{
            updateList(it)
        })
    }

    private fun updateList(categories: List<Category>?) {
        categoriesAdapter.submitList(categories)
    }

}