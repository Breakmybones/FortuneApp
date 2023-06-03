package com.example.fortuneproject

import android.app.Application
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponent
import com.example.feature_main_screen_impl.presentation.di.MainScreenComponentProvider
import com.example.feature_profile_screen_impl.presentation.di.ProfileScreenComponent
import com.example.feature_profile_screen_impl.presentation.di.ProfileScreenComponentProvider
import com.example.featureregistrationimpl.presentation.di.RegistrationComponent
import com.example.featureregistrationimpl.presentation.di.RegistrationComponentProvider
import timber.log.Timber

open class App: Application(), RegistrationComponentProvider, MainScreenComponentProvider, ProfileScreenComponentProvider {

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
}
