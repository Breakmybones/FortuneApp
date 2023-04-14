package com.example.common.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: com.example.common.viewmodel.ViewModelProviderFactory): ViewModelProvider.Factory
}