package com.example.feature_main_screen_impl.di

import com.google.gson.annotations.SerializedName

data class CardResponse (
    @SerializedName("description")
    val description: String?,
    @SerializedName("lasso")
    val lasso: String?,
    @SerializedName("suit")
    val suit: String?,
    @SerializedName("name")
    val name: String?,
)
