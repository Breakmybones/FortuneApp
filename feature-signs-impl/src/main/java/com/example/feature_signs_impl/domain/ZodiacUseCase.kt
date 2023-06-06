package com.example.feature_signs_impl.domain

import android.util.Log
import com.example.feature_signs_api.interfaces.ZodiacRepository
import com.example.feature_signs_api.model.ListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ZodiacUseCase(
    private val zodiacRepository: ZodiacRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(zodiac: String): ListResponse {
        return withContext(dispatcher) {
            Log.e("Usecase", "zodiacRepository.getCompatibility(zodiac)")
            zodiacRepository.getCompatibility(zodiac)
        }
    }
}