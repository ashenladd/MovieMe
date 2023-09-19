package com.example.movieme.data.source.remote.network

import com.example.movieme.data.source.remote.model.MovieListRes
import com.example.movieme.data.source.remote.model.MovieResultRes
import com.example.movieme.domain.model.MovieModel
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val movieService: MovieService):MovieRemoteDataSource {
    override suspend fun getMovieList(page: Int): Response<MovieListRes> {
        return movieService.getListMovie(page = page)
    }

    override suspend fun getMovie(movieId: Int): Response<MovieResultRes> {
        return movieService.getMovie(movieId = movieId)
    }
}