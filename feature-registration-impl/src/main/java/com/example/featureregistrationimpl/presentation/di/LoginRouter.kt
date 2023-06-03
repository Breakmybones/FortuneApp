package com.example.featureregistrationimpl.presentation.di

import android.content.Context
import com.example.feature_registration_api.domain.model.UserModel

interface LoginRouter {

    fun openRegister()

    fun openHome()

    fun openLogin(context: Context)
}