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
        email: String?,
        password: String?,
        dayOfBirth: String?,
        male: Boolean?,
    ) {
        withContext(dispatcher) {
            userRepository.registerUser(
                username = username,
                email = email,
                password = password,
                dayOfBirth = dayOfBirth,
                male = male
            )
        }
    }
}
