package com.example.movieme.feature.movies

import com.example.movieme.domain.model.MovieModel

sealed class MoviesViewEffect {
    object OnLoadingMovies : MoviesViewEffect()
    data class OnSuccessGetMoviesList(val listMovies: List<MovieModel>) : MoviesViewEffect()
    data class OnError(val message:String):MoviesViewEffect()
}