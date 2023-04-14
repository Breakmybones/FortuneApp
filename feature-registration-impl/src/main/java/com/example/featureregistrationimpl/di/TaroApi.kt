package com.example.featureregistrationimpl.di

import com.example.feature_registration_api.domain.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaroApi {

    @GET("/login")
    suspend fun loginUser(
        @Query("log") login: String?,
        @Query("password") password: String?
    ): UserResponse


    @POST("/user/register")
    suspend fun registerUser(@Body userData: UserResponse): Call<UserModel>

}
