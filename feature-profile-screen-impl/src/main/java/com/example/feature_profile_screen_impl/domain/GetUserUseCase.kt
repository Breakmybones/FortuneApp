package com.example.feature_profile_screen_impl.domain

import com.example.feature_profile_screen_api.interfaces.UserProfileRepository
import com.example.feature_profile_screen_api.model.UserProfileModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUseCase(
    private val userProfileRepository: UserProfileRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(
        email: String?,
    ): UserProfileModel {
        return withContext(dispatcher) {
            userProfileRepository.getUser(email)
        }
    }
}