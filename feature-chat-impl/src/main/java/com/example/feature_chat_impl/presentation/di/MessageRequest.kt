package com.example.feature_chat_impl.presentation.di

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class MessageRequest (
    @SerializedName("sender")
    val sender: String?,
    @SerializedName("receiver")
    val receiver: String?,
    @SerializedName("text")
    val text: String?
)