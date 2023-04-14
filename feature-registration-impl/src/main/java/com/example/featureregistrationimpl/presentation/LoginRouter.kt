package com.example.featureregistrationimpl.presentation

import android.content.Context
import com.example.feature_registration_api.domain.model.UserModel

interface LoginRouter {

    fun openRegister()

    fun openHome(context: Context)

    fun openLogin(context: Context)
}