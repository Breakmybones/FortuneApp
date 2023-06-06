package com.example.feature_alignment_impl.presentation.di

import com.example.feature_alignment_api.domain.interfaces.AlignmentRepository
import com.example.feature_alignment_impl.data.AlignmentRepositoryImpl
import com.example.feature_alignment_impl.di.AlignmentRequests
import com.example.feature_alignment_impl.domain.AlignmentsUseCase
import com.example.feature_alignment_impl.domain.CardByIdUseCase
import com.example.feature_alignment_impl.domain.CardsByIdUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AlignmentModule {

    @Provides
    fun provideAlignmentRequests (
        retrofit: Retrofit
    ): AlignmentRequests = retrofit.create(AlignmentRequests ::class.java)

    @Provides
    fun provideRegistrationRepository(
        alignmentApi : AlignmentRequests
    ): AlignmentRepository = AlignmentRepositoryImpl(alignmentApi)

    @Provides
    fun provideAlignmentsUseCase(
        repository: AlignmentRepository
    ): AlignmentsUseCase = AlignmentsUseCase(repository)

    @Provides
    fun provideCardByIdUseCase(
        repository: AlignmentRepository
    ): CardByIdUseCase = CardByIdUseCase(repository)

    @Provides
    fun provideCardsByIdUseCase(
        repository: AlignmentRepository
    ): CardsByIdUseCase = CardsByIdUseCase(repository)
}