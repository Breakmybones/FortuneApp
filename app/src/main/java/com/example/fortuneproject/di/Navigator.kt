package com.example.fortuneproject.di

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.example.featureregistrationimpl.presentation.LoginRouter
import com.example.featureregistrationimpl.presentation.RegisterRouter
import com.example.fortuneproject.MainActivity
import com.example.fortuneproject.R

class Navigator: LoginRouter, RegisterRouter {

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
        Log.e("controller", navController.toString())
        navController?.navigate(R.id.register_fragment)
    }

    override fun openHome(context: Context) {

    }

    override fun openLogin(context: Context) {
        MainActivity.start(context)
    }

}