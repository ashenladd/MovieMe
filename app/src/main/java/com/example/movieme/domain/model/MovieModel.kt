package com.example.movieme.domain.model

data class MovieModel(
    val id: Long? = null,
    val title: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val voteAverage: Double? = null,
    val popularity: Double? = null,
    val voteCount:Int? = null
)
