package com.android.damir.moviedb.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.damir.moviedb.ui.home.latest.LatestFragment
import com.android.damir.moviedb.ui.home.popular.PopularFragment
import com.android.damir.moviedb.ui.home.toprated.TopRatedFragment

class HomePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return PopularFragment()
            1 -> return TopRatedFragment()
            2 -> return LatestFragment()
        }
        return PopularFragment()
    }

}