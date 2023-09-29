package com.example.movieme.data.source.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
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
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("original_language")
    val language: String? = null,
)
