package com.example.feature_chat_impl.presentation.rv_chat

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_chat_impl.presentation.model.Message
import com.example.feature_sign_impl.databinding.SentBinding

class SentHolder(private val binding: SentBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message) {
        with(binding){
            txtSentMessage.text = message.text
        }
    }
}