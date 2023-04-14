package com.example.feature_registration_api.di

interface RegistrationApi {

    fun registerUser(email: String, password: String)

    fun addUserIntoFirebase(username: String, email: String, dayOfBirth: String)
}