package com.example.feature_profile_screen_impl.domain

import com.example.feature_profile_screen_api.interfaces.UserProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditUserUseCase(

    private val userProfileRepository: UserProfileRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main

) {
    suspend operator fun invoke(
        username: String?,
        email: String?,
        dayOfBirth: String?,
        male: Boolean?,
    ) {
        withContext(dispatcher) {
            userProfileRepository.setUser(
                username = username,
                email = email,
                dayOfBirth = dayOfBirth,
                male = male
            )
        }
    }
}