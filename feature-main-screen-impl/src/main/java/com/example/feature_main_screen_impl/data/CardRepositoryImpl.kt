package com.example.feature_main_screen_impl.data

import com.example.feature_main_screen_api.interfaces.CardRepository
import com.example.feature_main_screen_api.model.*
import com.example.feature_main_screen_impl.di.CardApi

class CardRepositoryImpl(
    private val api: CardApi
): CardRepository {
    override suspend fun getRandomCard(): CardModel {
        val response = api.getCard()[0]
        return CardModel(
            name = response.name,
            lasso = response.lasso,
            description = response.description,
            suit = response.suit
        )
    }

    override suspend fun getRandomDigit(): DigitModel {
        val response = api.getNumber()
        return DigitModel(
            number = response.number.toString(),
            description = response.description
        )
    }

    override suspend fun getRandomColor(): ColorModel {
        val response = api.getColor()
        return ColorModel(
            color = response.color,
            description = response.description
        )
    }

    override suspend fun getRandomYes(): YesModel {
        val response =  api.getYes()
        return YesModel(
            description = response.description
        )
    }

    override suspend fun getRandomCookie(): CookieModel {
        val response =  api.getCookie()
        return CookieModel(
            description = response.description
        )
    }
}