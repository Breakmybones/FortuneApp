package com.example.featureregistrationimpl.domain

import com.example.feature_registration_api.domain.interfaces.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant

class RegisterUserUseCase(

    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main

) {
    suspend operator fun invoke(
        username: String?,
        login: String?,
        password: String?,
        dayOfBirth: Instant?,
        male: Boolean?,
        icon: String?
    ) {
        withContext(dispatcher) {
            userRepository.registerUser(
                username = username,
                login = login,
                password = password,
                dayOfBirth = dayOfBirth,
                male = male,
                icon = icon
            )
        }
    }
}
