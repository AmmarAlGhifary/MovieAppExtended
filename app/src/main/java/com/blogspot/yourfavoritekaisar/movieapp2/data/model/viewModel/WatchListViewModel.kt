package com.blogspot.yourfavoritekaisar.movieapp2.data.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.AppDatabase
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.WatchListType
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model.WatchList
import javax.inject.Inject

class WatchListViewModel @Inject constructor(
    private val db: AppDatabase
) : ViewModel() {

    private val _all = MutableLiveData<List<WatchList>>()

    val all: LiveData<List<WatchList>>
        get() = _all

    private val _movies = MutableLiveData<List<WatchList>>()

    val movies: LiveData<List<WatchList>>
        get() = _movies

    private val _tvShows = MutableLiveData<List<WatchList>>()

    val tvShows: LiveData<List<WatchList>>
        get() = _tvShows

    init {
        getWatchList()
    }

    fun getWatchList() {
        val movies = db.movieDao().getAll()
        val tvShows = db.tvShowDao().getAll()

        val watchList = mutableListOf<WatchList>()

        watchList.addAll(
            movies.map { movie ->
                WatchList(
                    movie.id,
                    movie.title,
                    movie.overview,
                    movie.posterPath,
                    movie.backdropPath,
                    movie.rating,
                    movie.releaseDate,
                    WatchListType.MovieType
                )
            }
        )

        watchList.addAll(
            tvShows.map { tvShow ->
                WatchList(
                    tvShow.id,
                    tvShow.name,
                    tvShow.overview,
                    tvShow.posterPath,
                    tvShow.backdropPath,
                    tvShow.rating,
                    tvShow.firstAirDate,
                    WatchListType.TvShowType
                )
            }
        )

        _all.value = watchList
    }

    fun getMovies() {
        val movies = db.movieDao().getAll()

        val watchlist = movies.map { movie ->
            WatchList(
                movie.id,
                movie.title,
                movie.overview,
                movie.posterPath,
                movie.backdropPath,
                movie.rating,
                movie.releaseDate,
                WatchListType.MovieType
            )
        }

        _movies.value = watchlist
    }

    fun getTvShows() {
        val tvShows = db.tvShowDao().getAll()

        val watchlist = tvShows.map { tvShow ->
            WatchList(
                tvShow.id,
                tvShow.name,
                tvShow.overview,
                tvShow.posterPath,
                tvShow.backdropPath,
                tvShow.rating,
                tvShow.firstAirDate,
                WatchListType.TvShowType
            )
        }

        _tvShows.value = watchlist
    }
}