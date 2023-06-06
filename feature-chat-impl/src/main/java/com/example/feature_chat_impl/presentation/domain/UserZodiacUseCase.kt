package com.example.feature_chat_impl.presentation.domain

import android.util.Log
import com.example.feature_chat_api.interfaces.UserZodiacRepository
import com.example.feature_chat_api.model.UserZodiacInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserZodiacUseCase(
    private val userZodiacRepository: UserZodiacRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main

) {
    suspend operator fun invoke(
        zodiac: String,
        email: String
    ): UserZodiacInfo{
        return withContext(dispatcher) {
//            Log.e("UseCase", userZodiacRepository.getUserByZodiac(zodiac, email).toString())
            userZodiacRepository.getUserByZodiac(zodiac, email)
        }
    }
}