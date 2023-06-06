package com.example.feature_chat_impl.presentation.model

import java.time.LocalDateTime

data class Message (
    val text: String?,
    val sender: String,
    val receiver: String,
    val date: LocalDateTime?,
    val isSent: Boolean
)