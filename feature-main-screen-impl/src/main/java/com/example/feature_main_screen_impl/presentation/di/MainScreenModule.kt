package com.example.feature_main_screen_impl.presentation.di

import com.example.feature_main_screen_api.interfaces.CardRepository
import com.example.feature_main_screen_impl.data.CardRepositoryImpl
import com.example.feature_main_screen_impl.di.CardApi
import com.example.feature_main_screen_impl.domain.GetCardUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainScreenModule {

    @Provides
    fun provideCardApi(
        retrofit: Retrofit
    ): CardApi = retrofit.create(CardApi::class.java)

    @Provides
    fun provideRegistrationRepository(
        cardApi: CardApi
    ): CardRepository = CardRepositoryImpl(cardApi)

    @Provides
    fun provideGetCardUseCase(
        repository: CardRepository
    ): GetCardUseCase = GetCardUseCase(repository)

}