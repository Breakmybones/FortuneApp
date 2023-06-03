package com.example.fortuneproject.di

import com.example.feature_main_screen_impl.presentation.di.MainRouter
import com.example.feature_profile_screen_impl.presentation.di.EditProfileRouter
import com.example.feature_profile_screen_impl.presentation.di.ProfileRouter
import com.example.featureregistrationimpl.presentation.di.LoginRouter
import com.example.featureregistrationimpl.presentation.di.RegisterRouter
import com.example.featureregistrationimpl.presentation.di.ApplicationScope
import dagger.Module
import dagger.Provides


@Module
class NavigationModule {

    @ApplicationScope
    @Provides
    fun provideNavigator(): Navigator = Navigator()

    @ApplicationScope
    @Provides
    fun provideLoginRouter(navigator: Navigator): LoginRouter = navigator

    @ApplicationScope
    @Provides
    fun provideRegisterRouter(navigator: Navigator): RegisterRouter = navigator

    @ApplicationScope
    @Provides
    fun provideHomeRouter(navigator: Navigator): MainRouter = navigator

    @ApplicationScope
    @Provides
    fun provideProfileRouter(navigator: Navigator): ProfileRouter = navigator

    @ApplicationScope
    @Provides
    fun provideEditProfileRouter(navigator: Navigator): EditProfileRouter = navigator
}
