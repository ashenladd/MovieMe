package com.example.movieme.di

import com.example.movieme.data.source.remote.network.MovieConstant
import com.example.movieme.data.source.remote.network.MovieConstant.API_KEY
import com.example.movieme.data.source.remote.network.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideNetworkInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()

        httpClient.addNetworkInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header(
                    "Authorization",
                    "Bearer $API_KEY"
                )
                .header(
                    "accept",
                    "application/json"
                )
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return httpClient.build()
    }

    @Provides
    fun provideNetworkClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(MovieConstant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideNetworkService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}