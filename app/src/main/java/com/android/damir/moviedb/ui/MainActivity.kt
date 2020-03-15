package com.android.damir.moviedb.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.damir.moviedb.R
import com.android.damir.moviedb.ui.categories.CategoriesFragment
import com.android.damir.moviedb.ui.favorites.FavoritesFragment
import com.android.damir.moviedb.ui.home.HomeFragment
import com.android.damir.moviedb.ui.search.SearchFragment
import com.android.damir.moviedb.utils.ProgressBarController
import com.android.damir.moviedb.utils.TAG_CATEGORIES
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ProgressBarController{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNav()
        if(savedInstanceState == null){
            loadFragment(HomeFragment())
        }
    }

    override fun setProgressVisibility(visible: Boolean) {
        if(visible){
            progress_circular.visibility = View.VISIBLE
            fragment_container.visibility = View.INVISIBLE
        }else{
            progress_circular.visibility = View.GONE
            fragment_container.visibility = View.VISIBLE
        }
    }

    private fun loadFragment(fragment: Fragment, tag: String? = null) {
        setProgressVisibility(true)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
            .commit()
    }

    private fun setupBottomNav() {
        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.categories -> {
                    loadFragment(CategoriesFragment(), TAG_CATEGORIES)
                    true
                }
                R.id.search -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.favorites -> {
                    loadFragment(FavoritesFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }

    }
}