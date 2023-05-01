package com.example.fortuneproject

import android.content.Context
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponent
import com.example.featureregistrationimpl.presentation.LoginFragment
import com.example.featureregistrationimpl.presentation.LoginRouter
import com.example.featureregistrationimpl.presentation.RegistrationFragment
import com.example.featureregistrationimpl.presentation.di.ApplicationScope
import com.example.featureregistrationimpl.presentation.di.RegistrationComponent
import com.example.featureregistrationimpl.presentation.di.RegistrationModule
import com.example.fortuneproject.di.*
import com.example.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@ApplicationScope
@Component(
    modules = [
        RegistrationModule::class,
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

    fun injectApp(app: App)

    fun registerComponent(): RegistrationComponent.Builder

    fun mainScreenComponent(): MainScreenComponent.Builder

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}