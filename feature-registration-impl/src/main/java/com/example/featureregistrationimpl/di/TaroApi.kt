package com.example.featureregistrationimpl.di

import com.example.feature_registration_api.domain.model.UserModel
import retrofit2.Call
import retrofit2.http.*

interface TaroApi {

    @GET("/user/login/{login}/{password}")
    suspend fun loginUser(
        @Path("login") email: String?,
        @Path("password") password: String?
    ): UserResponse


    @POST("/user/register")
    suspend fun registerUser(@Body userData: UserResponse): Call<UserModel>

}
