package com.example.featureregistrationimpl.data

import android.util.Log
import com.example.feature_registration_api.domain.interfaces.UserRepository
import com.example.feature_registration_api.domain.model.UserModel
import com.example.featureregistrationimpl.di.TaroApi
import com.example.featureregistrationimpl.di.UserResponse

class UserRepositoryImpl(
    private val api: TaroApi
): UserRepository {
    override suspend fun loginUser(email: String?, password: String?): UserModel {
        val response = api.loginUser(email, password)
        return UserModel(
            username = response.username,
            email = response.email,
            password = response.password,
            dayOfBirth = response.birth.toString(),
            male = response.male,
        )
    }

    override suspend fun registerUser(
        username: String?,
        email: String?,
        password: String?,
        dayOfBirth: String?,
        male: Boolean?,
    ) {
        val userResponse = UserResponse(
            username = username,
            email = email,
            password = password,
            birth = dayOfBirth,
            male = male,
        )
        api.registerUser(userResponse)
    }
}