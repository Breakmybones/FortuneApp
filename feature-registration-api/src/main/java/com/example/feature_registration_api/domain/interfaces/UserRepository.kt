package com.example.feature_registration_api.domain.interfaces

import com.example.feature_registration_api.domain.model.UserModel
import java.time.Instant

interface UserRepository {

    suspend fun loginUser(email: String?, password: String?): UserModel

    suspend fun registerUser(
        username: String?,
        email: String?,
        password: String?,
        dayOfBirth: String?,
        male: Boolean?,
    )
}