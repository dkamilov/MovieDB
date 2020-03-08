package com.android.damir.moviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.damir.moviedb.R
import com.android.damir.moviedb.ui.categories.CategoriesFragment
import com.android.damir.moviedb.ui.favorites.FavoritesFragment
import com.android.damir.moviedb.ui.home.HomeFragment
import com.android.damir.moviedb.ui.search.SearchFragment
import com.android.damir.moviedb.utils.TAG_CATEGORIES
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNav()
        if(savedInstanceState == null){
            loadFragment(HomeFragment())
        }
    }

    private fun loadFragment(fragment: Fragment, tag: String? = null) {
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