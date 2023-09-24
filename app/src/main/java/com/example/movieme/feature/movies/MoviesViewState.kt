package com.example.movieme.feature.movies

import com.example.movieme.domain.model.MovieModel

data class MoviesViewState(
    val movies: List<MovieModel> = emptyList(),
    val page: Int = 1,
)