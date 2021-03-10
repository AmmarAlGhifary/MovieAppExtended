package com.blogspot.yourfavoritekaisar.movieapp2.data.module

import android.app.Application
import com.blogspot.yourfavoritekaisar.movieapp2.view.activity.MovieDetailActivity
import com.blogspot.yourfavoritekaisar.movieapp2.view.activity.TvShowDetailActivity
import com.blogspot.yourfavoritekaisar.movieapp2.view.fragment.MovieFragment
import com.blogspot.yourfavoritekaisar.movieapp2.view.fragment.TvShowFragment
import com.blogspot.yourfavoritekaisar.movieapp2.view.fragment.WatchListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        VIewModelModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(fragment: MovieFragment)

    fun inject(fragment: TvShowFragment)

    fun inject(fragment: WatchListFragment)

    fun inject(activity: MovieDetailActivity)

    fun inject(activity: TvShowDetailActivity)
}