package com.example.feature_alignment_impl.di

import retrofit2.http.GET
import retrofit2.http.Path

interface AlignmentRequests {

    @GET("/taro/count/{count}")
    suspend fun getCards(
        @Path("count") count: Int
    ): List <CardsModel>

    @GET("/taro/{id}")
    suspend fun getCardById(
        @Path("id") id: Long
    ): CardsModel

    @GET("/taro/id/{listId}")
    suspend fun getCardsById(
        @Path("listId") listId: String
    ): List<CardsModel>
}
