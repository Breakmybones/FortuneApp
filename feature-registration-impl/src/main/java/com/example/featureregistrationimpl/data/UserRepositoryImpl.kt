package com.example.featureregistrationimpl.data

import com.example.feature_registration_api.domain.interfaces.UserRepository
import com.example.feature_registration_api.domain.model.UserModel
import com.example.featureregistrationimpl.di.TaroApi
import com.example.featureregistrationimpl.di.UserResponse
import java.time.Instant

class UserRepositoryImpl(
    private val api: TaroApi
): UserRepository {
    override suspend fun loginUser(login: String?, password: String?): UserModel {
        val response = api.loginUser(login, password)
        return UserModel(
            username = response.username,
            login = response.login,
            password = response.password,
            dayOfBirth = response.birth.toString(),
            male = response.male,
            icon = response.icon
        )
    }

    override suspend fun registerUser(
        username: String?,
        login: String?,
        password: String?,
        dayOfBirth: Instant?,
        male: Boolean?,
        icon: String?
    ) {
        val userResponse = UserResponse(
            username = username,
            login = login,
            password = password,
            birth = dayOfBirth,
            male = male,
            icon = icon
        )
        api.registerUser(userResponse)
    }
}