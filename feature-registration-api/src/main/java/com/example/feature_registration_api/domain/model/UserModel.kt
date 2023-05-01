package com.example.feature_registration_api.domain.model

data class UserModel(
    val username: String?,
    val email: String?,
    val password: String?,
    val dayOfBirth: String?,
    val male: Boolean?,
)