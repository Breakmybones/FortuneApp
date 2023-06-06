package com.example.feature_alignment_impl.domain

import com.example.feature_alignment_api.domain.interfaces.AlignmentRepository
import com.example.feature_alignment_api.domain.models.CardsListModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardsByIdUseCase(
    private val tarotRepository: AlignmentRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(listId: String): CardsListModel {
        return withContext(dispatcher) {
            tarotRepository.getCardsById(listId)
        }
    }
}