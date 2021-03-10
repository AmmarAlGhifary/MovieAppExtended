package com.blogspot.yourfavoritekaisar.movieapp2.data.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model.TvShow
import com.blogspot.yourfavoritekaisar.movieapp2.data.repository.TvShowsRepository
import javax.inject.Inject


class TvShowsViewModel @Inject constructor(
    private val repository: TvShowsRepository
) : ViewModel() {

    private val _popularTvShows = MutableLiveData<List<TvShow>>()

    val popularTvShows: LiveData<List<TvShow>>
        get() = _popularTvShows

    private val _topRatedTvShows = MutableLiveData<List<TvShow>>()

    val topRatedTvShows: LiveData<List<TvShow>>
        get() = _topRatedTvShows

    private val _onAirTvShows = MutableLiveData<List<TvShow>>()

    val onAirTvShows: LiveData<List<TvShow>>
        get() = _onAirTvShows

    private val _error = MutableLiveData<Boolean>()

    val error: LiveData<Boolean>
        get() = _error

    init {
        getPopularTvShows()
        getTopRatedTvShows()
        getOnAirTvShows()
    }

    fun getPopularTvShows(page: Int = 1) {
        repository.getPopularTvShows(
            page,
            ::onPopularTvShowsFetched,
            ::onError
        )
    }

    private fun onPopularTvShowsFetched(tvShows: List<TvShow>) {
        _popularTvShows.value = tvShows
    }

    fun getTopRatedTvShows(page: Int = 1) {
        repository.getTopRatedTvShows(
            page,
            ::onTopRatedTvShowsFetched,
            ::onError
        )
    }

    private fun onTopRatedTvShowsFetched(tvShows: List<TvShow>) {
        _topRatedTvShows.value = tvShows
    }

    fun getOnAirTvShows(page: Int = 1) {
        repository.getOnAirTvShows(
            page,
            ::onOnAirTvShowsFetched,
            ::onError
        )
    }

    private fun onOnAirTvShowsFetched(tvShows: List<TvShow>) {
        _onAirTvShows.value = tvShows
    }

    private fun onError() {
        _error.value = true
    }
}