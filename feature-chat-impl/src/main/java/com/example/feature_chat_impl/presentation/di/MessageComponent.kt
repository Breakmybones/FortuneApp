package com.example.feature_chat_impl.presentation.di

import com.example.feature_chat_impl.presentation.fragment.ChatFragment
import dagger.Subcomponent

@Subcomponent(modules = [MessageModule::class])
interface MessageComponent {

    fun injectChatFragment(fragment: ChatFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MessageComponent
    }
}