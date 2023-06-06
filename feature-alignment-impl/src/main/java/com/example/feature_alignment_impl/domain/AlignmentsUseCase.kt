package com.example.feature_alignment_impl.domain

import com.example.feature_alignment_api.domain.interfaces.AlignmentRepository
import com.example.feature_alignment_api.domain.models.CardsListModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlignmentsUseCase(
    private val tarotRepository: AlignmentRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(count: Int): CardsListModel {
        return withContext(dispatcher) {
            tarotRepository.getCards(count)
        }
    }
}
