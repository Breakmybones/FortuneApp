package com.example.feature_main_screen_impl.data

import android.util.Log
import com.example.feature_main_screen_api.interfaces.CardRepository
import com.example.feature_main_screen_api.model.CardModel
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

    override suspend fun getRandomDigit(): Map<String, String> {
        return api.getNumber()
    }

    override suspend fun getRandomColor(): Map<String, String> {
        return api.getColor()
    }

    override suspend fun getRandomYes(): String {
        return api.getYes()
    }

    override suspend fun getRandomCookie(): String {
        return api.getCookie()
    }
}