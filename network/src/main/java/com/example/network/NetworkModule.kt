package com.example.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideHttpClient(
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(gsonFactory)
        .baseUrl("http://localhost:8080")
        .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}