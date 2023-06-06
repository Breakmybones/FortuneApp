package com.example.feature_chat_impl.presentation.di

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MessageAPI {

    @GET("/message/get/{sender}/{receiver}")
    suspend fun getBySenderAndReceiver(
        @Path("sender") sender: String?,
        @Path("receiver") receiver: String?
    ): List<MessageResponse>

    @POST("/message/save")
    suspend fun saveComment(@Body messageData: MessageRequest): MessageResponse
}