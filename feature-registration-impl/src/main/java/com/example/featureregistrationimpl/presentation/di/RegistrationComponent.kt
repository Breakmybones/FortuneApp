package com.example.featureregistrationimpl.presentation.di

import com.example.featureregistrationimpl.presentation.LoginFragment
import com.example.featureregistrationimpl.presentation.LoginRouter
import com.example.featureregistrationimpl.presentation.RegistrationFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import retrofit2.Retrofit

@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {

    fun injectLoginFragment(fragment: LoginFragment)

    fun injectRegisterFragment(fragment: RegistrationFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): RegistrationComponent
    }

}