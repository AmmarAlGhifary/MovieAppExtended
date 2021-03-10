package com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.response

import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model.TvShow
import com.google.gson.annotations.SerializedName

data class GetTvShowResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val tvShows: List<TvShow>,
    @SerializedName("total_pages") val pages: Int
)

