package com.android.damir.moviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.damir.moviedb.R
import com.android.damir.moviedb.ui.categories.CategoriesFragment
import com.android.damir.moviedb.ui.favorites.FavoritesFragment
import com.android.damir.moviedb.ui.home.HomeFragment
import com.android.damir.moviedb.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNav()
        if(savedInstanceState == null){
            loadFragment(HomeFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
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
                    loadFragment(CategoriesFragment())
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