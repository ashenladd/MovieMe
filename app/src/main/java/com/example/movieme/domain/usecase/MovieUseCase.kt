package com.example.movieme.domain.usecase

import com.example.movieme.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun getMovieList(
        page: Int,
    ) = movieRepository.getMovieList(page = page)

    suspend fun getMovie(
        movieId: Int,
    ) = movieRepository.getMovie(movieId = movieId)
}