package com.example.feature_main_screen_api.interfaces

import com.example.feature_main_screen_api.model.CardModel

interface CardRepository {

    suspend fun getRandomCard(): CardModel

    suspend fun getRandomDigit(): Map<String, String>

    suspend fun getRandomColor(): Map<String, String>

    suspend fun getRandomYes(): String

    suspend fun getRandomCookie(): String

}
