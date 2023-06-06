package com.example.feature_alignment_api.domain.interfaces

import com.example.feature_alignment_api.domain.models.CardsListModel
import com.example.feature_alignment_api.domain.models.CardsModel

interface AlignmentRepository {
    suspend fun getCards(count: Int): CardsListModel
    suspend fun getCardById(id: Long): CardsModel
    suspend fun getCardsById(listId: String): CardsListModel
}
