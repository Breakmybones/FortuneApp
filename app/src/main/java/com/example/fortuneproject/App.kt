package com.example.fortuneproject

import android.app.Application
import com.example.featureregistrationimpl.presentation.di.RegistrationComponent
import com.example.featureregistrationimpl.presentation.di.RegistrationComponentProvider
import timber.log.Timber

open class App: Application(), RegistrationComponentProvider {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appComponent = DaggerAppComponent
            .builder()
            .context(applicationContext)
//            .router(Navigator())
            .build()

    }
    companion object {
        lateinit var appComponent: AppComponent
    }

//    override fun provideRegistrationComponent(): RegistrationComponent {
//        TODO("Not yet implemented")
//    }


    override fun provideRegistrationComponent(): RegistrationComponent {
        return appComponent.registerComponent().build()
    }

}