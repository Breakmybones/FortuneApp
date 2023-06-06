package com.example.feature_signs_impl.presentation.di

import com.example.feature_signs_api.interfaces.ZodiacRepository
import com.example.feature_signs_impl.data.ZodiacRepositoryImpl
import com.example.feature_signs_impl.di.ZodiacAPI
import com.example.feature_signs_impl.domain.ZodiacUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ZodiacModule {
    @Provides
    fun provideZodiacRequests (
        retrofit: Retrofit
    ): ZodiacAPI = retrofit.create(ZodiacAPI ::class.java)

    @Provides
    fun provideZodiacsUseCase(
        repository: ZodiacRepository
    ): ZodiacUseCase = ZodiacUseCase(repository)

    @Provides
    fun provideZodiacRepository(
        zodiacApi : ZodiacAPI
    ): ZodiacRepository = ZodiacRepositoryImpl(zodiacApi)
}