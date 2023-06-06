package com.example.feature_main_screen_impl.di.response

import com.google.gson.annotations.SerializedName

data class YesResponse (
    @SerializedName("description")
    val description: String?
        )