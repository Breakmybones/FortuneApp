package com.example.feature_main_screen_impl.di

import retrofit2.http.GET

interface CardApi {

    @GET("/taro/1")
    suspend fun getCard(): CardResponse

}
