package com.example.feature_signs_impl.presentation.di

import com.example.feature_signs_impl.presentation.fragments.FriendshipListFragment
import com.example.feature_signs_impl.presentation.fragments.LoveListFragment
import com.example.feature_signs_impl.presentation.fragments.SelectDialogFragment
import com.example.feature_signs_impl.presentation.fragments.ZodiacInfoFragment
import dagger.Subcomponent

@Subcomponent(modules = [ZodiacModule::class])
interface ZodiacComponent {

    fun injectFriendshipListFragment(fragment: FriendshipListFragment)

    fun injectLoveListFragment(fragment: LoveListFragment)

    fun injectSelectDialogFragment(fragment: SelectDialogFragment)

    fun injectZodiacInfoFragment(fragment: ZodiacInfoFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ZodiacComponent
    }
}