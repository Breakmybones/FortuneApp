package com.example.feature_profile_screen_impl.data

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("birth")
    val birth: String?,
    @SerializedName("male")
    val male: Boolean?,
)