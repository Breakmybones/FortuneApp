package com.example.fortuneproject.di

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.example.feature_main_screen_impl.presentation.di.MainRouter
import com.example.featureregistrationimpl.presentation.LoginRouter
import com.example.featureregistrationimpl.presentation.RegisterRouter
import com.example.fortuneproject.MainActivity
import com.example.fortuneproject.R

class Navigator: LoginRouter, RegisterRouter, MainRouter {

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
        navController?.navigate(R.id.home_fragment)
    }

    override fun openLogin(context: Context) {
        MainActivity.start(context)
    }

    override fun openProfile() {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override fun openDigit() {
        TODO("Not yet implemented")
    }

    override fun openYesOrNo() {
        TODO("Not yet implemented")
    }

    override fun openCookie() {
        TODO("Not yet implemented")
    }

}