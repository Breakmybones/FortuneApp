package com.example.feature_main_screen_impl.data

import com.example.feature_main_screen_api.interfaces.CardRepository
import com.example.feature_main_screen_api.model.CardModel
import com.example.feature_main_screen_impl.di.CardApi

class CardRepositoryImpl(
    private val api: CardApi
): CardRepository {
    override suspend fun getRandomCard(): CardModel {
        val response = api.getCard()
        return CardModel(
            name = response.name,
            lasso = response.lasso,
            description = response.description
        )
    }
}