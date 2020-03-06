package com.android.damir.moviedb

import android.app.Application
import com.android.damir.moviedb.data.room.MoviesDatabase
import timber.log.Timber

class MovieDBApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        MoviesDatabase.initialize(this)
    }
}