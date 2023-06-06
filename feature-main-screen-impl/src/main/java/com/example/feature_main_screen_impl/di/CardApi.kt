package com.example.feature_main_screen_impl.di

import com.example.feature_main_screen_api.model.DigitModel
import com.example.feature_main_screen_impl.di.response.CardResponse
import com.example.feature_main_screen_impl.di.response.ColorResponse
import com.example.feature_main_screen_impl.di.response.DigitResponse
import com.example.feature_main_screen_impl.di.response.YesResponse
import retrofit2.http.GET

interface CardApi {

    @GET("/taro/count/1")
    suspend fun getCard(): List<CardResponse>

    @GET("/random/yesno")
    suspend fun getYes(): YesResponse

    @GET("/random/number")
    suspend fun getNumber(): DigitResponse

    @GET("/random/color")
    suspend fun getColor(): ColorResponse

    @GET("/random/cookie")
    suspend fun getCookie(): ColorResponse
}
