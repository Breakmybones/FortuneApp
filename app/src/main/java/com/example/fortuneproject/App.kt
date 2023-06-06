package com.example.fortuneproject

import android.app.Application
import com.example.feature_alignment_impl.presentation.di.AlignmentComponent
import com.example.feature_alignment_impl.presentation.di.AlignmentComponentProvider
import com.example.feature_chat_impl.presentation.di.MessageComponent
import com.example.feature_chat_impl.presentation.di.MessageComponentProvider
import com.example.feature_chat_impl.presentation.di.UserZodiacComponent
import com.example.feature_chat_impl.presentation.di.UserZodiacComponentProvider
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponent
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponentProvider
import com.example.feature_profile_screen_impl.presentation.di.ProfileScreenComponent
import com.example.feature_profile_screen_impl.presentation.di.ProfileScreenComponentProvider
import com.example.feature_signs_impl.presentation.di.ZodiacComponent
import com.example.feature_signs_impl.presentation.di.ZodiacComponentProvider
import com.example.featureregistrationimpl.presentation.di.RegistrationComponent
import com.example.featureregistrationimpl.presentation.di.RegistrationComponentProvider
import timber.log.Timber

open class App: Application(), RegistrationComponentProvider, MainScreenComponentProvider, ProfileScreenComponentProvider, AlignmentComponentProvider,
    ZodiacComponentProvider, UserZodiacComponentProvider, MessageComponentProvider {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appComponent = DaggerAppComponent
            .builder()
            .context(applicationContext)
            .build()
    }
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun provideRegistrationComponent(): RegistrationComponent {
        return appComponent.registerComponent().build()
    }

    override fun provideMainScreenComponent(): MainScreenComponent {
        return appComponent.mainScreenComponent().build()
    }

    override fun provideProfileScreenComponent(): ProfileScreenComponent {
        return appComponent.profileComponent().build()
    }

    override fun provideAlignmentComponent(): AlignmentComponent {
        return appComponent.alignmentComponent().build()
    }

    override fun provideZodiacComponent(): ZodiacComponent {
        return appComponent.zodiacComponent().build()
    }

    override fun provideMessageComponent(): MessageComponent {
        return appComponent.messageComponent().build()
    }

    override fun provideUserZodiacComponent(): UserZodiacComponent {
        return appComponent.userZodiacComponent().build()
    }
}
