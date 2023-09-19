package com.example.movieme.domain.usecase

import com.example.movieme.domain.model.MovieModel
import com.example.movieme.domain.repository.MovieRepository
import com.example.movieme.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun getMovieList(
        page:Int =1
    ) = movieRepository.getMovieList(page = page)

    suspend fun getMovie(
        movieId:Int
    ) = movieRepository.getMovie(movieId = movieId)
}