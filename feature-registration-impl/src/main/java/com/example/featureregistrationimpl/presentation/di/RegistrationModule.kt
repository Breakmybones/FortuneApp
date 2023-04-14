package com.example.featureregistrationimpl.presentation.di

import com.example.feature_registration_api.domain.interfaces.UserRepository
import com.example.featureregistrationimpl.data.UserRepositoryImpl
import com.example.featureregistrationimpl.di.TaroApi
import com.example.featureregistrationimpl.domain.LoginUserUseCase
import com.example.featureregistrationimpl.domain.RegisterUserUseCase
import com.example.featureregistrationimpl.presentation.LoginRouter
import com.example.featureregistrationimpl.presentation.LoginViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RegistrationModule {

    @Provides
    fun provideTaroApi(
        retrofit: Retrofit
    ): TaroApi = retrofit.create(TaroApi::class.java)

    @Provides
    fun provideRegistrationRepository(
        taroApi: TaroApi
    ): UserRepository = UserRepositoryImpl(taroApi)

    @Provides
    fun provideRegisterUseCase(
        repository: UserRepository
    ): RegisterUserUseCase = RegisterUserUseCase(repository)

    @Provides
    fun provideLoginUseCase(
        repository: UserRepository
    ): LoginUserUseCase = LoginUserUseCase(repository)

//    @Provides
//    fun provideLoginRouter(): LoginRouter = navigator
}