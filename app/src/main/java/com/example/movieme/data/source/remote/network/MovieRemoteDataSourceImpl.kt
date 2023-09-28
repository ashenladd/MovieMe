package com.example.movieme.data.source.remote.network

import com.example.movieme.data.source.remote.model.MovieListRes
import com.example.movieme.data.source.remote.model.MovieResultRes
import com.example.movieme.data.source.remote.network.MovieConstant.API_KEY
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val movieService: MovieService) :
    MovieRemoteDataSource {
    override suspend fun getMovieList(page: Int): Response<MovieListRes> {
        return movieService.getMovieList(
            apiKey = API_KEY,
            page = page
        )
    }

    override suspend fun getMovie(movieId: Long): Response<MovieResultRes> {
        return movieService.getMovie(
            apiKey = API_KEY,
            movieId = movieId
        )
    }
}