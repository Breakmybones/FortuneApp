package com.example.feature_main_screen_impl.di

import retrofit2.http.GET

interface CardApi {

    @GET("/taro/count/1")
    suspend fun getCard(): List<CardResponse>

    @GET("/random/yesno")
    suspend fun getYes(): String

    @GET("/random/number")
    suspend fun getNumber(): Map<String, String>

    @GET("/random/color")
    suspend fun getColor(): Map<String, String>

    @GET("/random/cookie")
    suspend fun getCookie(): String
}
