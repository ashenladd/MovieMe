package com.example.movieme.data.source.remote.network

import com.example.movieme.data.source.remote.model.MovieListRes
import com.example.movieme.data.source.remote.model.MovieResultRes
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovieList(
        page: Int,
    ): Response<MovieListRes>

    suspend fun getMovie(
        movieId: Int,
    ): Response<MovieResultRes>
}