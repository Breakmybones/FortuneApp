package com.example.feature_profile_screen_impl.data.model

import android.net.Uri

data class UserProfileModel(
    val username: String?,
    val email: String?,
    val dayOfBirth: String?,
    val male: Boolean?,
    val sign: String?,
    val icon: Uri?
)