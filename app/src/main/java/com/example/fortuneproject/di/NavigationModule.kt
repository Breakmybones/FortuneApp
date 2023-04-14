package com.example.fortuneproject.di

import com.example.featureregistrationimpl.presentation.LoginRouter
import com.example.featureregistrationimpl.presentation.RegisterRouter
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
}