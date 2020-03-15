package com.android.damir.moviedb.ui.categories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.damir.moviedb.R
import com.android.damir.moviedb.data.api.Category
import com.android.damir.moviedb.ui.BaseFragment
import com.android.damir.moviedb.ui.adapter.CategoriesAdapter
import com.android.damir.moviedb.ui.adapter.OnCategoryClickListener
import com.android.damir.moviedb.utils.CATEGORY_ID_EXTRA
import com.android.damir.moviedb.utils.CATEGORY_NAME_EXTRA
import com.android.damir.moviedb.utils.showMovieByCategory
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : BaseFragment(), OnCategoryClickListener {

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var categoriesViewModel: CategoriesViewModel

    override val layoutRes: Int = R.layout.fragment_categories

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setupToolbar()
        setupRecyclerView()
        setupObservers()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCategoryClicked(category: Category) {
        val bundle = Bundle().apply {
            putLong(CATEGORY_ID_EXTRA, category.id.toLong())
            putString(CATEGORY_NAME_EXTRA, category.name)
        }
        val fragment = MovieByCategoryFragment().apply {
            arguments = bundle
        }
        requireActivity()
            .supportFragmentManager
            .showMovieByCategory(R.id.fragment_container, fragment)
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.categories)
    }

    private fun setupRecyclerView() {
        categoriesAdapter = CategoriesAdapter(this, progressBarController)
        recyclerView.adapter = categoriesAdapter
    }

    private fun setupObservers() {
        categoriesViewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        categoriesViewModel.getCategories().observe(viewLifecycleOwner, Observer{
            updateList(it)
        })
    }

    private fun updateList(categories: List<Category>) {
        categoriesAdapter.submitList(categories)
    }
}