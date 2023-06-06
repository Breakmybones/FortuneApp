package com.example.feature_chat_impl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_chat_api.model.ListMessage
import com.example.feature_chat_api.model.MessageInfo
import com.example.feature_chat_impl.presentation.domain.AddMessageUseCase
import com.example.feature_chat_impl.presentation.domain.MessageUseCase
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import kotlinx.coroutines.launch

class MessageViewModel(
    private val router: ZodiacRouter,
    private val messageUseCase: MessageUseCase,
    private val addMessageUseCase: AddMessageUseCase
):  ViewModel() {

    private val _message = MutableLiveData<Result<MessageInfo>>()
    val message: LiveData<Result<MessageInfo>>
        get() = _message

    private val _receiverMessages = MutableLiveData<Result<ListMessage>>()
    val receiverMessages: LiveData<Result<ListMessage>>
        get() = _receiverMessages


    private var _error: MutableLiveData<Exception> = MutableLiveData()

    fun addMessage(sender: String?, receiver: String?, text: String?){
        viewModelScope.launch {
            try {
                addMessageUseCase(sender, receiver, text)
                Log.e("add view model", "good")
            } catch (ex: Exception) {
                _message.value = Result.failure(ex)
                _error.value = ex
                Log.e("Add VM", ex.toString())
            }
        }
    }

    fun getByReceiver(sender: String, receiver: String){
        viewModelScope.launch {
            try {
                val receiverMessages = messageUseCase(sender, receiver)
                _receiverMessages.value = Result.success(receiverMessages)
                Log.e("MessageVM", receiverMessages.toString())
            } catch (ex: Exception) {
                _message.value = Result.failure(ex)
                _error.value = ex
                Log.e("Receiver VM", ex.toString())
            }
        }
    }

    companion object{
        fun provideFactory(
            router: ZodiacRouter,
            messageUseCase: MessageUseCase,
            addMessageUseCase: AddMessageUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MessageViewModel(
                    router,
                    messageUseCase,
                    addMessageUseCase
                )
            }
        }
    }

}