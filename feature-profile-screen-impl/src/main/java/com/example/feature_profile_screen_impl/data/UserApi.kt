package com.example.feature_profile_screen_impl.data

import com.example.feature_profile_screen_api.model.UserProfileModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.Call

interface UserApi {

    @GET("/user/getUser/{email}")
    suspend fun getUser(
        @Path("email") email: String?,
    ): UserResponse

    @POST("/user/update")
    suspend fun updateUser(@Body userData: UserResponse): Call<UserProfileModel>
}