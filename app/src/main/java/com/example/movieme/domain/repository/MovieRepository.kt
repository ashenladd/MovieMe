package com.example.movieme.domain.repository

import com.example.movieme.data.source.remote.model.MovieListRes
import com.example.movieme.data.source.remote.model.MovieResultRes
import com.example.movieme.domain.model.MovieModel
import com.example.movieme.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {
    suspend fun getMovieList(
        page:Int =1
    ): Flow<ApiResult<List<MovieModel>>>

    suspend fun getMovie(
        movieId:Int
    ): Flow<ApiResult<MovieModel?>>
}