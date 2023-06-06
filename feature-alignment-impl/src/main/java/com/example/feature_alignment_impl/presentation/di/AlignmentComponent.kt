package com.example.feature_alignment_impl.presentation.di

import com.example.feature_alignment_impl.presentation.fragments.*
import dagger.Subcomponent

@Subcomponent(modules = [AlignmentModule::class])
interface AlignmentComponent {

    fun injectAlignmentsFragment(fragment: AlignmentsFragment)

    fun injectSelectCardsFragment(fragment: SelectCardsFragment)

    fun injectAlignmentFragment(fragment: AlignmentFragment)

    fun injectDetailFragment(fragment: DetailFragment)

    fun injectDialogFragment(fragment: CustomDialogFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): AlignmentComponent
    }
}