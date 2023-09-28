package com.example.movieme.feature.home

interface HomeListener {
    fun navigateToMovieDetail(movieId:Long)
    fun navigatoToMovies(featureName:String)
}