package com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model

import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("first_air_date") val firstAirDate: String
)