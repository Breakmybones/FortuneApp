package com.example.feature_profile_screen_impl.data

import com.example.feature_profile_screen_api.interfaces.UserProfileRepository
import com.example.feature_profile_screen_api.model.UserProfileModel

class UserProfileRepositoryImpl(
    private val api: UserApi
): UserProfileRepository {
    override suspend fun getUser(email: String?): UserProfileModel {
        val response = api.getUser(email)
        return UserProfileModel(
            username = response.username,
            email = response.email,
            dayOfBirth = response.birth.toString(),
            male = response.male,
        )
    }

    override suspend fun setUser(
        username: String?,
        email: String?,
        dayOfBirth: String?,
        male: Boolean?,
    ) {
        val userResponse = UserResponse(
            username = username,
            email = email,
            birth = dayOfBirth,
            male = male,
        )
        api.updateUser(userResponse)
    }
}