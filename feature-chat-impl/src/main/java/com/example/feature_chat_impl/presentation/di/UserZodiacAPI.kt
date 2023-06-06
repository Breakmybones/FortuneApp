package com.example.feature_chat_impl.presentation.di

import com.example.feature_signs_impl.di.ZodiacModel
import retrofit2.http.GET
import retrofit2.http.Path

interface UserZodiacAPI {

    @GET("/user/get/{zodiac}/{email}")
    suspend fun getUserByZodiac(
        @Path("zodiac") zodiac: String,
        @Path("email") email: String
    ): UserZodiacModel
}