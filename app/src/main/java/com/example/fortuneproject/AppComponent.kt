package com.example.fortuneproject

import android.content.Context
import com.example.feature_alignment_impl.presentation.di.AlignmentComponent
import com.example.feature_alignment_impl.presentation.di.AlignmentModule
import com.example.feature_alignment_impl.presentation.fragments.AlignmentFragment
import com.example.feature_alignment_impl.presentation.fragments.AlignmentsFragment
import com.example.feature_alignment_impl.presentation.fragments.SelectCardsFragment
import com.example.feature_alignment_impl.presentation.routers.AlignmentRouter
import com.example.feature_alignment_impl.presentation.routers.AlignmentsRouter
import com.example.feature_alignment_impl.presentation.routers.SelectCardsRouter
import com.example.feature_chat_impl.presentation.di.MessageComponent
import com.example.feature_chat_impl.presentation.di.MessageModule
import com.example.feature_chat_impl.presentation.di.UserZodiacComponent
import com.example.feature_chat_impl.presentation.di.UserZodiacModule
import com.example.feature_chat_impl.presentation.fragment.ChatFragment
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponent
import com.example.feature_profile_screen_impl.presentation.di.ProfileRouter
import com.example.feature_profile_screen_impl.presentation.di.ProfileScreenComponent
import com.example.feature_signs_impl.presentation.di.ZodiacComponent
import com.example.feature_signs_impl.presentation.di.ZodiacModule
import com.example.feature_signs_impl.presentation.fragments.FriendshipListFragment
import com.example.feature_signs_impl.presentation.fragments.LoveListFragment
import com.example.feature_signs_impl.presentation.fragments.SelectDialogFragment
import com.example.feature_signs_impl.presentation.fragments.ZodiacInfoFragment
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import com.example.featureregistrationimpl.presentation.fragment.LoginFragment
import com.example.featureregistrationimpl.presentation.di.LoginRouter
import com.example.featureregistrationimpl.presentation.fragment.RegistrationFragment
import com.example.featureregistrationimpl.presentation.di.ApplicationScope
import com.example.featureregistrationimpl.presentation.di.RegistrationComponent
import com.example.featureregistrationimpl.presentation.di.RegistrationModule
import com.example.fortuneproject.di.*
import com.example.network.NetworkModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        RegistrationModule::class,
        AlignmentModule::class,
        ZodiacModule::class,
        MessageModule::class,
        UserZodiacModule::class,
        NetworkModule::class,
        NavigationModule::class,
        AppModule::class,
        SubcomponentsModule::class,
    ]
)
interface AppComponent: MainDependencies {

    fun injectLoginFragment(loginFragment: LoginFragment)

    fun injectRegisterFragment(registrationFragment: RegistrationFragment)

    fun injectLoginRouter(loginRouter: LoginRouter)

    fun injectProfileRouter(profileRouter: ProfileRouter)

    fun injectApp(app: App)

    fun registerComponent(): RegistrationComponent.Builder

    fun mainScreenComponent(): MainScreenComponent.Builder

    fun profileComponent(): ProfileScreenComponent.Builder

    fun alignmentComponent(): AlignmentComponent.Builder

    fun injectAlignmentsFragment(alignmentsFragment: AlignmentsFragment)

    fun injectAlignmentFragment(alignmentFragment: AlignmentFragment)

    fun injectSelectCardsFragment(selectCardsFragment: SelectCardsFragment)

    fun injectAlignmentsRouter(alignmentsRouter: AlignmentsRouter)

    fun injectAlignmentRouter(alignmentRouter: AlignmentRouter)

    fun injectSelectCardsRouter(selectCardsRouter: SelectCardsRouter)

    fun injectZodiacInfoFragment(zodiacInfoFragment: ZodiacInfoFragment)

    fun injectLoveListFragment(loveListFragment: LoveListFragment)

    fun injectChatFragment(chatFragment: ChatFragment)

    fun injectSelectDialogFragment(selectFragment: SelectDialogFragment)

    fun injectFriendshipListFragment(friendshipListFragment: FriendshipListFragment)

    fun injectZodiacRouter(zodiacRouter: ZodiacRouter)

    fun inject(activity: MainActivity)

    fun zodiacComponent(): ZodiacComponent.Builder

    fun messageComponent(): MessageComponent.Builder

    fun userZodiacComponent(): UserZodiacComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}