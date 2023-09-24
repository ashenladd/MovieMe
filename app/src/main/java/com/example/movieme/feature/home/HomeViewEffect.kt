package com.example.movieme.feature.home

import com.example.movieme.domain.model.MovieModel
import com.example.movieme.feature.movies.MoviesViewEffect

sealed class HomeViewEffect {
    object OnLoadingMovies : HomeViewEffect()
    data class OnSuccessGetMoviesList(val listMovies: List<MovieModel>) : HomeViewEffect()
    data class OnError(val message:String): HomeViewEffect()
}