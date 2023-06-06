package com.example.feature_chat_impl.presentation.di

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class MessageResponse (
    @SerializedName("sender")
    val sender: String?,
    @SerializedName("receiver")
    val receiver: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("date")
    val date: List<Int>?,
        )
