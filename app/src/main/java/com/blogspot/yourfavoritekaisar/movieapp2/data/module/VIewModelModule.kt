package com.blogspot.yourfavoritekaisar.movieapp2.data.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.viewModel.MoviesViewModel
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.viewModel.TvShowsViewModel
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.viewModel.WatchListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VIewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindsMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowsViewModel::class)
    abstract fun bindsTvShowsViewModel(viewModel: TvShowsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WatchListViewModel::class)
    abstract fun bindsWatchListViewModel(viewModel: WatchListViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}