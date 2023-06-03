package com.example.featureregistrationimpl.domain

import com.example.feature_registration_api.domain.interfaces.UserRepository
import com.example.feature_registration_api.domain.model.UserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginUserUseCase(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(
        email: String?,
        password: String?
    ): UserModel {
        return withContext(dispatcher) {
            userRepository.loginUser(email, password)
        }
    }
}
