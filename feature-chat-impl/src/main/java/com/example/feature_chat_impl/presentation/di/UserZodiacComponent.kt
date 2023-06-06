package com.example.feature_chat_impl.presentation.di

import dagger.Subcomponent

@Subcomponent(modules = [UserZodiacModule::class])
interface UserZodiacComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): UserZodiacComponent
    }
}