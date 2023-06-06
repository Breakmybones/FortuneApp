package com.example.feature_chat_impl.presentation.rv_chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_chat_impl.presentation.model.Message
import com.example.feature_sign_impl.databinding.ReceiveBinding
import com.example.feature_sign_impl.databinding.SentBinding

class MessageAdapter(var messageList: List<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType==1){
            ReceiveHolder(ReceiveBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else{
            SentHolder(SentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        return if(currentMessage.isSent){
            ITEM_SENT
        }else{
            ITEM_RECEIVE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messageList[position]
        when (holder) {
            is SentHolder -> holder.bind(message)
            is ReceiveHolder -> holder.bind(message)
        }
    }
}



