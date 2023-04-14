package com.example.featureregistrationimpl.di

import com.google.gson.annotations.SerializedName
import java.time.Instant

data class UserResponse(
    @SerializedName("username")
    val username: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("birth")
    val birth: Instant?,
    @SerializedName("male")
    val male: Boolean?,
    @SerializedName("icon")
    val icon: String?
)