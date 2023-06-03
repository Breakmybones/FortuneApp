package com.example.feature_main_screen_impl.presentation.di

import com.example.feature_main_screen_impl.presentation.fragment.*
import dagger.Subcomponent


@Subcomponent(modules = [MainScreenModule::class])
interface MainScreenComponent {

    fun injectMainFragment(fragment: HomeFragment)

    fun injectCardFragment(fragment: CardFragment)

    fun injectColorFragment(fragment: ColorFragment)

    fun injectDigitFragment(fragment: DigitFragment)

    fun injectYesFragment(fragment: YesFragment)

    fun injectCookieFragment(fragment: CookieFragment)


    @Subcomponent.Builder
    interface Builder {
        fun build(): MainScreenComponent
    }

}
