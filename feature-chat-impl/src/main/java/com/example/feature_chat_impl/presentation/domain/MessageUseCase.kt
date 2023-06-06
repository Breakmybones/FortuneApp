package com.example.feature_chat_impl.presentation.domain

import android.util.Log
import com.example.feature_chat_api.interfaces.MessageRepository
import com.example.feature_chat_api.model.ListMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MessageUseCase(
    private val messageRepository: MessageRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(
        sender: String?,
        receiver: String?
    ): ListMessage {
        return withContext(dispatcher) {
            Log.e("UseCase", messageRepository.getBySenderAndReceiver(sender, receiver).toString())
            messageRepository.getBySenderAndReceiver(sender, receiver)
        }
    }
}