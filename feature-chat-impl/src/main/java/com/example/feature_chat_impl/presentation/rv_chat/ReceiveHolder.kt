package com.example.feature_chat_impl.presentation.rv_chat

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_chat_impl.presentation.model.Message
import com.example.feature_sign_impl.R
import com.example.feature_sign_impl.databinding.ReceiveBinding
import com.example.feature_signs_impl.databinding.ItemZodiacBinding

class ReceiveHolder(private val binding: ReceiveBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message) {
        with(binding){
            txtReceiveMessage.text = message.text
        }
    }
}