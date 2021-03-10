package com.blogspot.yourfavoritekaisar.movieapp2.data.dao

import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.response.GetMoviesResponse
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.response.GetTvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "fa2c849fca1204cdc46abea78adbbb86",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "fa2c849fca1204cdc46abea78adbbb86",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "fa2c849fca1204cdc46abea78adbbb86",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String = "fa2c849fca1204cdc46abea78adbbb86",
        @Query("page") page: Int
    ): Call<GetTvShowResponse>


    @GET("tv/top_rated")
    fun getTopRatedTvShows(
        @Query("api_key") apiKey: String = "fa2c849fca1204cdc46abea78adbbb86",
        @Query("page") page: Int
    ): Call<GetTvShowResponse>

    @GET("tv/on_the_air")
    fun getOnAirTvShows(
        @Query("api_key") apiKey: String = "fa2c849fca1204cdc46abea78adbbb86",
        @Query("page") page: Int
    ): Call<GetTvShowResponse>
}