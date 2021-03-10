package com.blogspot.yourfavoritekaisar.movieapp2.data.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model.Movie
import com.blogspot.yourfavoritekaisar.movieapp2.data.repository.MoviesRepository
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()

    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<List<Movie>>()

    val topRatedMovies: LiveData<List<Movie>>
        get() = _topRatedMovies

    private val _upcomingMovies = MutableLiveData<List<Movie>>()

    val upcomingMovies: LiveData<List<Movie>>
        get() = _upcomingMovies

    private val _error = MutableLiveData<Boolean>()

    val error: LiveData<Boolean>
        get() = _error

    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    fun getPopularMovies(page: Int = 1) {
        repository.getPopularMovies(
            page,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        _popularMovies.value = movies
    }

    fun getTopRatedMovies(page: Int = 1) {
        repository.getTopRatedMovies(
            page,
            ::onTopRatedMoviesFetched,
            ::onError
        )
    }

    private fun onTopRatedMoviesFetched(movies: List<Movie>) {
        _topRatedMovies.value = movies
    }

    fun getUpcomingMovies(page: Int = 1) {
        repository.getUpcomingMovies(
            page,
            ::onUpcomingMoviesFetched,
            ::onError
        )
    }

    private fun onUpcomingMoviesFetched(movies: List<Movie>) {
        _upcomingMovies.value = movies
    }

    private fun onError() {
        _error.value = true
    }
}