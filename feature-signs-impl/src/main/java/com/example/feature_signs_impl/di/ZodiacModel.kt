package com.example.feature_signs_impl.di

import com.google.gson.annotations.SerializedName

data class ZodiacModel(
    @SerializedName("zodiac")
    val name: String,
    @SerializedName("love")
    val love: Int,
    @SerializedName("friendship")
    val friendship: Int
)
