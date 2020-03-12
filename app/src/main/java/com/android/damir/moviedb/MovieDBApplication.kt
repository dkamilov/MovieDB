package com.android.damir.moviedb

import android.app.Application
import com.android.damir.moviedb.data.room.MoviesDatabase
import timber.log.Timber

class MovieDBApplication : Application(){

    //TODO: 1.Add favorites fragment
    //TODO: 3.Query images of movie(without language query)
    //TODO: 4.Try CardView for items

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        MoviesDatabase.initialize(this)
    }
}