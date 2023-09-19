package com.example.movieme.data.repository

import com.example.movieme.data.source.remote.mapper.toModel
import com.example.movieme.data.source.remote.network.MovieRemoteDataSource
import com.example.movieme.domain.model.MovieModel
import com.example.movieme.domain.repository.MovieRepository
import com.example.movieme.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val dataSource: MovieRemoteDataSource) :
    MovieRepository {
    override suspend fun getMovieList(page: Int): Flow<ApiResult<List<MovieModel>>> {
        return flow {
            val response = dataSource.getMovieList(page = page)
            if (response.isSuccessful) {
                emit(
                    ApiResult.Success(
                        _data = response.body()?.results.orEmpty().map { it.toModel() })
                )
            } else {
                val errorResponse = response.errorBody()?.toString().orEmpty()
                emit(ApiResult.Error(errorResponse))
            }
        }.onStart {
            emit(ApiResult.Loading(arrayListOf()))
        }
    }

    override suspend fun getMovie(movieId: Int): Flow<ApiResult<MovieModel?>> {
        return flow {
            val response = dataSource.getMovie(movieId)
            if (response.isSuccessful) {
                emit(
                    ApiResult.Success(
                        _data = response.body()?.toModel())
                )
            } else {
                val errorResponse = response.errorBody()?.toString().orEmpty()
                emit(ApiResult.Error(errorResponse))
            }
        }.onStart {
            emit(ApiResult.Loading())
        }
    }
}