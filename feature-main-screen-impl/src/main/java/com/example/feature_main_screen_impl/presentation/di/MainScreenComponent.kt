package com.example.feature_main_screen_impl.presentation.di

import com.example.feature_main_screen_impl.presentation.fragment.HomeFragment
import dagger.Subcomponent


@Subcomponent(modules = [MainScreenModule::class])
interface MainScreenComponent {

    fun injectMainFragment(fragment: HomeFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainScreenComponent
    }

}
