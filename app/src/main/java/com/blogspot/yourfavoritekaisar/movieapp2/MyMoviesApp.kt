package com.blogspot.yourfavoritekaisar.movieapp2

import android.app.Application
import com.blogspot.yourfavoritekaisar.movieapp2.data.module.AppComponent
import com.blogspot.yourfavoritekaisar.movieapp2.data.module.DaggerAppComponent

class MyMoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}
lateinit var appComponent: AppComponent