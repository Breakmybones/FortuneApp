package com.example.feature_chat_impl.presentation.di

import com.example.feature_chat_api.interfaces.MessageRepository
import com.example.feature_chat_impl.data.MessageRepositoryImpl
import com.example.feature_chat_impl.presentation.domain.AddMessageUseCase
import com.example.feature_chat_impl.presentation.domain.MessageUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MessageModule {

    @Provides
    fun provideZodiacRequests (
        retrofit: Retrofit
    ): MessageAPI = retrofit.create(MessageAPI ::class.java)

    @Provides
    fun provideAddMessageUseCase(
        repository: MessageRepository
    ): AddMessageUseCase = AddMessageUseCase(repository)

    @Provides
    fun provideMessageUseCase(
        repository: MessageRepository
    ): MessageUseCase = MessageUseCase(repository)

    @Provides
    fun provideMessageRepository(
        messageApi : MessageAPI
    ): MessageRepository = MessageRepositoryImpl(messageApi)

}
