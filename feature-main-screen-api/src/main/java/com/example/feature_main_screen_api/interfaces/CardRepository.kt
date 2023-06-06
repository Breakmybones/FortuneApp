package com.example.feature_main_screen_api.interfaces

import com.example.feature_main_screen_api.model.*

interface CardRepository {

    suspend fun getRandomCard(): CardModel

    suspend fun getRandomDigit(): DigitModel

    suspend fun getRandomColor(): ColorModel

    suspend fun getRandomYes(): YesModel

    suspend fun getRandomCookie(): CookieModel

}
