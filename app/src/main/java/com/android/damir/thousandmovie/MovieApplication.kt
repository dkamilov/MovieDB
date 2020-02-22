package com.android.damir.thousandmovie

import android.app.Application
import com.android.damir.thousandmovie.data.room.MoviesDatabase
import timber.log.Timber

class MovieApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        MoviesDatabase.initialize(this)
    }
}