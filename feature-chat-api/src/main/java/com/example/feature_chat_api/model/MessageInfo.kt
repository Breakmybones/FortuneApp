package com.example.feature_chat_api.model

import java.time.LocalDateTime

data class MessageInfo (
    val sender: String?,
    val receiver: String?,
    val text: String?,
    val date: LocalDateTime?
)