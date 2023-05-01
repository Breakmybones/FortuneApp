package com.example.feature_main_screen_impl.domain

import com.example.feature_main_screen_api.interfaces.CardRepository
import com.example.feature_main_screen_api.model.CardModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCardUseCase(
    private val cardRepository: CardRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(): CardModel {
        return withContext(dispatcher) {
            cardRepository.getRandomCard()
        }
    }
}