package com.example.movieme.di

import com.example.movieme.data.source.remote.network.MovieRemoteDataSource
import com.example.movieme.data.source.remote.network.MovieRemoteDataSourceImpl
import com.example.movieme.data.source.remote.network.MovieService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object DataSourceModule {
    fun ProvideDataSource(movieService: MovieService):MovieRemoteDataSource = MovieRemoteDataSourceImpl(
        movieService
    )
}