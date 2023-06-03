package com.example.fortuneproject.di

import com.example.fortuneproject.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApp(): App {
        return App()
    }
}
