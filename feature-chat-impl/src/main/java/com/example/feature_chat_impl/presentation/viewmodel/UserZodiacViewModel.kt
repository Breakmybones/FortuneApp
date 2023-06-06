package com.example.feature_chat_impl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_chat_api.model.UserZodiacInfo
import com.example.feature_chat_impl.presentation.domain.UserZodiacUseCase
import com.example.feature_signs_impl.domain.ZodiacUseCase
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import kotlinx.coroutines.launch

class UserZodiacViewModel(
    private val router: ZodiacRouter,
    private val userZodiacUseCase: UserZodiacUseCase
):  ViewModel() {

    private val _user = MutableLiveData<Result<UserZodiacInfo>>()
    val user: LiveData<Result<UserZodiacInfo>>
        get() = _user

    private var _error: MutableLiveData<Exception> = MutableLiveData()

    fun getUser(zodiac: String, email: String){
        viewModelScope.launch {
            try {
                val user = userZodiacUseCase(zodiac, email)
                _user.value = Result.success(user)
//                Log.e("error", user?.email.toString())
            } catch (ex: Exception) {
                _user.value = Result.failure(ex)
                _error.value = ex
                Log.e("error", ex.toString())
            }
        }
    }

    companion object{
        fun provideFactory(
            router: ZodiacRouter,
            userZodiacUseCase: UserZodiacUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UserZodiacViewModel(
                    router,
                    userZodiacUseCase
                )
            }
        }
    }
}