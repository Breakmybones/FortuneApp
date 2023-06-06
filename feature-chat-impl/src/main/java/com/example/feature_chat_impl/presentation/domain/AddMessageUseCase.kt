package com.example.feature_chat_impl.presentation.domain

import android.util.Log
import com.example.feature_chat_api.interfaces.MessageRepository
import com.example.feature_chat_api.model.ListMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddMessageUseCase(
    private val messageRepository: MessageRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(
        sender: String?,
        receiver: String?,
        text: String?,
    ) {
        withContext(dispatcher) {
            Log.e("UseCase", listOf(sender, receiver, text).toString())
            messageRepository.saveMessage(sender, receiver, text)
        }
    }
}