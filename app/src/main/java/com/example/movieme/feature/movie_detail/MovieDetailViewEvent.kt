package com.example.movieme.feature.movie_detail

sealed class MovieDetailViewEvent {
    data class RefreshPage(val movieId: Long) : MovieDetailViewEvent()
    data class LoadPage(val movieId: Long) : MovieDetailViewEvent()
}