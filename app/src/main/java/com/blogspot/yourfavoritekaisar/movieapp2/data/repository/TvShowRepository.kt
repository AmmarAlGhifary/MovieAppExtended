package com.blogspot.yourfavoritekaisar.movieapp2.data.repository

import com.blogspot.yourfavoritekaisar.movieapp2.data.dao.Api
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model.TvShow
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.response.GetTvShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowsRepository @Inject constructor(private val api: Api) {

    fun getPopularTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getPopularTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getTopRatedTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getTopRatedTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getOnAirTvShows(
        page: Int = 1,
        onSuccess: (movies: List<TvShow>) -> Unit,
        onError: () -> Unit
    ) {
        api.getOnAirTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}