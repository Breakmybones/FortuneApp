package com.example.feature_main_screen_impl.domain

import com.example.feature_main_screen_api.interfaces.CardRepository
import com.example.feature_main_screen_api.model.CookieModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCookieUseCase(
    private val cardRepository: CardRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): CookieModel {
        return withContext(dispatcher) {
            cardRepository.getRandomCookie()
        }
    }
}