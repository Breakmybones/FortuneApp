package com.example.feature_profile_screen_api.interfaces

import com.example.feature_profile_screen_api.model.UserProfileModel

interface UserProfileRepository {

    suspend fun getUser(email: String?): UserProfileModel

    suspend fun setUser(username: String?, email: String?, dayOfBirth: String?, male: Boolean?)
}