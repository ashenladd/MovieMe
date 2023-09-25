package com.example.movieme.feature.home

import com.example.movieme.domain.model.MovieModel

data class HomeViewState (
    val recentMovies: List<MovieModel> = emptyList(),
    val popularMovies: List<MovieModel> = emptyList()
)