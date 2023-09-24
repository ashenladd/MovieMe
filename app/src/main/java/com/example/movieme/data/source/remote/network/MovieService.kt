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
    suspend fun getMovieList(
        @Query("api_key") apiKey:String,
        @Query("page") page:Int = 1
    ):Response<MovieListRes>

    @GET(MOVIE_DETAIL)
    suspend fun getMovie(
        @Query("api_key") apiKey:String,
        @Path("movieId") movieId:Int
    ):Response<MovieResultRes>

}