package com.android.damir.moviedb.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.damir.moviedb.R
import com.android.damir.moviedb.ui.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : BaseFragment() {

    private val tabs = arrayOf(
        R.string.popular,
        R.string.top_rated,
        R.string.latest)

    override val layoutRes: Int = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupViewPager()
    }

    private fun setupToolbar(){
        (activity as AppCompatActivity).setSupportActionBar(homeToolbar)
        (activity as AppCompatActivity).supportActionBar?.elevation = 0F
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.home)
    }

    private fun setupViewPager() {
        homeViewPager.adapter = HomePagerAdapter(requireActivity())
        TabLayoutMediator(homeTabs, homeViewPager){tab, position->
            tab.text = resources.getString(tabs[position])
        }.attach()
    }
}
