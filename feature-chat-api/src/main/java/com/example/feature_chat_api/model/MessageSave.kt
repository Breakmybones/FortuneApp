package com.example.feature_chat_api.model

import java.time.LocalDateTime

data class MessageSave (
    val sender: String?,
    val receiver: String?,
    val text: String?
)