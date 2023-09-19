package com.example.movieme.di

import com.example.movieme.data.repository.MovieRepositoryImpl
import com.example.movieme.data.source.remote.network.MovieRemoteDataSource
import com.example.movieme.domain.repository.MovieRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    fun provideRepository(movieRemoteDataSource: MovieRemoteDataSource): MovieRepository = MovieRepositoryImpl(
        movieRemoteDataSource
    )
}