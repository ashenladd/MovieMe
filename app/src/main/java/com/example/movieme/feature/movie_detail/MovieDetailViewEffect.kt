package com.example.movieme.feature.movie_detail

import com.example.movieme.domain.model.MovieModel
import com.example.movieme.feature.movies.MoviesViewEffect

sealed class MovieDetailViewEffect {
    object OnLoadingMovies : MovieDetailViewEffect()
    object OnSuccessGetMovie : MovieDetailViewEffect()
    data class OnError(val message:String): MovieDetailViewEffect()
}