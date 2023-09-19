package com.example.movieme.di

import com.example.movieme.data.source.remote.network.MovieConstant
import com.example.movieme.data.source.remote.network.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun ProvideNetworkInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun ProvideNetworkClient(interceptor: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(MovieConstant.BASE_URL)
            .client(interceptor)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun ProvideNetworkService(retrofit: Retrofit) : MovieService = retrofit.create(MovieService::class.java)
}