package com.example.featureregistrationimpl.presentation.di

import com.example.featureregistrationimpl.presentation.fragment.LoginFragment
import com.example.featureregistrationimpl.presentation.fragment.RegistrationFragment
import dagger.Subcomponent

@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {

    fun injectLoginFragment(fragment: LoginFragment)

    fun injectRegisterFragment(fragment: RegistrationFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): RegistrationComponent
    }

}