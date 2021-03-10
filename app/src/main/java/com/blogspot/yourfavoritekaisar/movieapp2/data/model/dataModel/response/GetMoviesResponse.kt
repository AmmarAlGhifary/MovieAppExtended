package com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.response

import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model.Movie
import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int
)