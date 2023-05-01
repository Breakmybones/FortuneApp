package com.example.featureregistrationimpl.di

import com.google.gson.annotations.SerializedName
import java.time.Instant

data class UserResponse(
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("birth")
    val birth: String?,
    @SerializedName("male")
    val male: Boolean?,
)