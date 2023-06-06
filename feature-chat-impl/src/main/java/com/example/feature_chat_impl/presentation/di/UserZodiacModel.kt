package com.example.feature_chat_impl.presentation.di

import com.google.gson.annotations.SerializedName

data class UserZodiacModel (
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("birth")
    val birth: String?,
    @SerializedName("male")
    val male: Boolean?,
)