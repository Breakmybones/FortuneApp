package com.example.feature_alignment_impl.data

import com.example.feature_alignment_api.domain.interfaces.AlignmentRepository
import com.example.feature_alignment_api.domain.models.CardsListModel
import com.example.feature_alignment_api.domain.models.CardsModel
import com.example.feature_alignment_impl.di.AlignmentRequests

class AlignmentRepositoryImpl(
    private val api: AlignmentRequests
): AlignmentRepository
     {
         override suspend fun getCards(count: Int): CardsListModel {
             val value = ArrayList<CardsModel>()
             val response = api.getCards(count)
             response.forEach {
                 value.add(
                     CardsModel(
                         id = it.id,
                         dignity = it.dignity,
                         description = it.description,
                         lasso = it.lasso,
                         suit = it.suit,
                         name = it.name
                     )
                 )
             }
             return CardsListModel(value)
         }

         override suspend fun getCardById(id: Long): CardsModel {
             val response = api.getCardById(id)
             return CardsModel(
                 response.id,
                 response.dignity,
                 response.description,
                 response.lasso,
                 response.suit,
                 response.name)
         }

         override suspend fun getCardsById(listId: String): CardsListModel {
             val value = ArrayList<CardsModel>()
             val response = api.getCardsById(listId)
             response.forEach {
                 value.add(
                     CardsModel(
                         id = it.id,
                         dignity = it.dignity,
                         description = it.description,
                         lasso = it.lasso,
                         suit = it.suit,
                         name = it.name
                     )
                 )
             }
             return CardsListModel(value)
         }
     }