package com.example.feature_profile_screen_impl.presentation.di

import com.example.feature_profile_screen_api.interfaces.UserProfileRepository
import com.example.feature_profile_screen_impl.data.UserApi
import com.example.feature_profile_screen_impl.data.UserProfileRepositoryImpl
import com.example.feature_profile_screen_impl.domain.EditUserUseCase
import com.example.feature_profile_screen_impl.domain.GetUserUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ProfileScreenModule {

    @Provides
    fun provideProfileApi(
        retrofit: Retrofit
    ): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    fun provideProfileRepository(
        userApi: UserApi
    ):  UserProfileRepository = UserProfileRepositoryImpl(userApi)

    @Provides
    fun provideEditUserUseCase(
        repository: UserProfileRepository
    ): EditUserUseCase = EditUserUseCase(repository)

    @Provides
    fun provideGetUserUseCase(
        repository: UserProfileRepository
    ): GetUserUseCase = GetUserUseCase(repository)

}
