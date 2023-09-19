package com.example.movieme.data.source.remote.network

import com.example.movieme.data.source.remote.model.MovieResultRes
import com.example.movieme.data.source.remote.model.MovieListRes
import com.example.movieme.data.source.remote.network.MovieConstant.MOVIE_DETAIL
import com.example.movieme.data.source.remote.network.MovieConstant.MOVIE_LIST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET(MOVIE_LIST)
    suspend fun getListMovie(
        @Query("api_key") apiKey:String = "a5bce38b74a0a7d44e0209b162b12d75",
        @Query("page") page:Int = 1
    ):Response<MovieListRes>

    @GET("$MOVIE_DETAIL/{movieId}")
    suspend fun getMovie(
        @Query("api_key") apiKey:String = "a5bce38b74a0a7d44e0209b162b12d75",
        @Path("movieId") movieId:Int
    ):Response<MovieResultRes>

}