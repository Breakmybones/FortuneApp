package com.example.feature_chat_impl.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.feature_chat_api.interfaces.MessageRepository
import com.example.feature_chat_api.model.ListMessage
import com.example.feature_chat_api.model.MessageInfo
import com.example.feature_chat_impl.presentation.di.MessageAPI
import com.example.feature_chat_impl.presentation.di.MessageRequest
import java.time.LocalDateTime

class MessageRepositoryImpl(
    private val api: MessageAPI
)
    : MessageRepository {
    override suspend fun saveMessage(sender: String?, receiver: String?, text: String?) {
        val messageResponse = MessageRequest(
            sender = sender,
            receiver = receiver,
            text = text
        )
        Log.e("Repository", messageResponse.toString())
        api.saveComment(messageResponse)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getBySenderAndReceiver(sender: String?, receiver: String?): ListMessage {
        val value = ArrayList<MessageInfo>()
        val response = api.getBySenderAndReceiver(sender, receiver)
        Log.e("Repository", response.toString())
        response.forEach {
            value.add(
                MessageInfo(
                    sender = it.sender,
                    receiver = it.receiver,
                    text = it.text,
                    date = convertDate(it.date!!)
                )
            )
        }
        return ListMessage(value)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertDate(list: List<Int>): LocalDateTime{
        return LocalDateTime.of(list[0], list[1], list[2], list[3], list[4], list[5], list[6])
    }
}