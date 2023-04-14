package com.example.featureregistrationimpl.presentation.di

import com.example.featureregistrationimpl.presentation.LoginFragment
import com.example.featureregistrationimpl.presentation.LoginRouter
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import retrofit2.Retrofit

@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {

    fun injectLoginFragment(fragment: LoginFragment)

    @Subcomponent.Builder
    interface Builder {
//        @BindsInstance
//        fun router(loginRouter: LoginRouter): Builder
//        @BindsInstance
//        fun taroApi(retrofit: Retrofit): Builder
//        @BindsInstance
//        fun router(loginRouter: LoginRouter): Builder
//        @BindsInstance
//        fun router(loginRouter: LoginRouter): Builder
        fun build(): RegistrationComponent
    }
//
//    @Subcomponent.Factory
//    interface Factory {
//        @BindsInstance
//        fun router(loginRouter: LoginRouter): Builder
//        @BindsInstance
//        fun taroApi(retrofit: Retrofit): Builder
//        fun create(): RegistrationComponent
//    }

}