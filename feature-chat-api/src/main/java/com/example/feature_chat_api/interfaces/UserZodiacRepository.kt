package com.example.feature_chat_api.interfaces

import com.example.feature_chat_api.model.UserZodiacInfo

interface UserZodiacRepository {

    suspend fun getUserByZodiac(zodiac: String, email: String): UserZodiacInfo
}