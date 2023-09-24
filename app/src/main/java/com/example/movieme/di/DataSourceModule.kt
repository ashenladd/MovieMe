package com.example.movieme.di

import com.example.movieme.data.source.remote.network.MovieRemoteDataSource
import com.example.movieme.data.source.remote.network.MovieRemoteDataSourceImpl
import com.example.movieme.data.source.remote.network.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideDataSource(movieService: MovieService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(
            movieService
        )
}