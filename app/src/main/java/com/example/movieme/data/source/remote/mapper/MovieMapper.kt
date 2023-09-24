package com.example.movieme.data.source.remote.mapper

import com.example.movieme.data.source.remote.model.MovieResultRes
import com.example.movieme.domain.model.MovieModel

fun MovieResultRes.toModel() = MovieModel(
    id = id ?: -1,
    title = title.orEmpty(),
    overview = overview.orEmpty(),
    posterPath = posterPath.orEmpty(),
    backdropPath = backdropPath.orEmpty(),
//    rating = rating ?: 0.0,
    releaseDate = releaseDate.orEmpty(),
    voteAverage = voteAverage ?: 0.0,
    popularity = popularity ?: 0.0
)