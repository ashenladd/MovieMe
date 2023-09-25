package com.example.movieme.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResultRes(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
//    @SerializedName("vote_average")
//    val rating: Double? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
)
