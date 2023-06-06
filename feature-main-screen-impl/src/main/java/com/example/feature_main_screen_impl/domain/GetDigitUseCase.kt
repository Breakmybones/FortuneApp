package com.example.feature_main_screen_impl.domain

import com.example.feature_main_screen_api.interfaces.CardRepository
import com.example.feature_main_screen_api.model.DigitModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDigitUseCase(
    private val cardRepository: CardRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): DigitModel {
        return withContext(dispatcher) {
            cardRepository.getRandomDigit()
        }
    }
}