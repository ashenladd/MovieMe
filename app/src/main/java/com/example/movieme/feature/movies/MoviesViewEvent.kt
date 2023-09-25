package com.example.movieme.feature.movies

sealed class MoviesViewEvent {
    object NextPage : MoviesViewEvent()
    object PrevPage : MoviesViewEvent()
}