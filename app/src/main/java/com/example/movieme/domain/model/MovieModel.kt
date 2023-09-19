package com.example.movieme.domain.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    val id: Long,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val rating: Float,
    val releaseDate: String,
    val voteAverage:Float,
    val popularity:Float
)
