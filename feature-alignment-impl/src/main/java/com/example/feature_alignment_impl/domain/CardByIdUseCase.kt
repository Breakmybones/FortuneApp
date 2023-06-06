package com.example.feature_alignment_impl.domain

import com.example.feature_alignment_api.domain.interfaces.AlignmentRepository
import com.example.feature_alignment_api.domain.models.CardsModel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardByIdUseCase(
    private val tarotRepository: AlignmentRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(id: Long): CardsModel {
        return withContext(dispatcher) {
            tarotRepository.getCardById(id)
        }
    }
}