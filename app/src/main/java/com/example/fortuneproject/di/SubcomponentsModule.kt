package com.example.fortuneproject.di

import com.example.feature_main_screen_impl.presentation.di.MainScreenComponent
import com.example.feature_profile_screen_impl.presentation.di.ProfileScreenComponent
import com.example.featureregistrationimpl.presentation.di.RegistrationComponent
import dagger.Module

@Module(subcomponents = [RegistrationComponent::class, MainScreenComponent::class, ProfileScreenComponent::class])
class SubcomponentsModule {
}
