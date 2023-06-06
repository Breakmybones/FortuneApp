package com.example.feature_alignment_impl.di

import com.google.gson.annotations.SerializedName

data class CardsModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("dignity")
    val dignity: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("lasso")
    val lasso: String,
    @SerializedName("suit")
    val suit: String?,
    @SerializedName("name")
    val name: String?
)
