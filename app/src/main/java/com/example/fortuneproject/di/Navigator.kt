package com.example.fortuneproject.di

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.example.feature_main_screen_impl.presentation.di.MainRouter
import com.example.feature_profile_screen_impl.presentation.di.EditProfileRouter
import com.example.feature_profile_screen_impl.presentation.di.ProfileRouter
import com.example.featureregistrationimpl.presentation.di.LoginRouter
import com.example.featureregistrationimpl.presentation.di.RegisterRouter
import com.example.fortuneproject.MainActivity
import com.example.fortuneproject.R

class Navigator: LoginRouter, RegisterRouter, MainRouter, ProfileRouter, EditProfileRouter {

    private var navController: NavController? = null

    fun initialize(navController: NavController) {
        this.navController = navController
    }

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    override fun openRegister() {
        navController?.navigate(R.id.register_fragment)
    }

    override fun openHome() {
        Log.e("navigator", navController.toString())
        navController?.navigate(R.id.home_fragment)
    }

    override fun openLogin(context: Context) {
        MainActivity.start(context)
    }

    override fun openProfile() {
        navController?.navigate(R.id.profile_fragment)
    }

    override fun openFortune() {
        TODO("Not yet implemented")
    }

    override fun openZodiac() {
        TODO("Not yet implemented")
    }

    override fun openNumbers() {
        TODO("Not yet implemented")
    }

    override fun openCard() {
        navController?.navigate(R.id.card_fragment)
    }

    override fun openColor() {
        navController?.navigate(R.id.color_fragment)
    }

    override fun openDigit() {
        navController?.navigate(R.id.digit_fragment)
    }

    override fun openYesOrNo() {
        navController?.navigate(R.id.yes_fragment)
    }

    override fun openCookie() {
        navController?.navigate(R.id.cookie_fragment)
    }

    override fun openChangeProfile() {
        navController?.navigate(R.id.edit_profile_fragment)
    }

    override fun openHomeFragment() {
        Log.e("navigator", navController.toString())
        navController?.navigate(R.id.home_fragment)
    }
}
