package com.example.feature_profile_screen_impl.presentation.di

import com.example.feature_profile_screen_impl.presentation.fragment.EditProfileFragment
import com.example.feature_profile_screen_impl.presentation.fragment.MainProfileFragment
import dagger.Subcomponent

@Subcomponent(modules = [ProfileScreenModule::class])
interface ProfileScreenComponent {

    fun injectProfileFragment(fragment: MainProfileFragment)

    fun injectEditProfileFragment(fragment: EditProfileFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ProfileScreenComponent
    }
}