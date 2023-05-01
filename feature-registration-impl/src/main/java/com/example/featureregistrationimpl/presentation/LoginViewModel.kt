package com.example.featureregistrationimpl.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_registration_api.domain.model.UserModel
import com.example.featureregistrationimpl.domain.LoginUserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val router: LoginRouter,
    private val loginUserUseCase: LoginUserUseCase,
): ViewModel() {


    private val _userLiveData = MutableLiveData<UserModel?>(null)
    val userLiveData: LiveData<UserModel?>
        get() = _userLiveData

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get()  = _error


    fun loginUser(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
                if (!loginUserUseCase(email, password).username.isNullOrEmpty()) {
                    _userLiveData.value = loginUserUseCase(email, password)
                }
            }
            catch (error: Throwable) {
                _error.value = error
            }
        }
    }

    fun registerClick() {
        router.openRegister()
    }

    fun loginClick(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
                if (!loginUserUseCase(email, password).email.isNullOrEmpty())
                    _userLiveData.value = loginUserUseCase(email, password)
                router.openHome()
            }
            catch (error: Throwable) {
                _error.value = error
            }
        }
        router.openHome()
    }

    companion object {
        fun provideFactory(
            router: LoginRouter,
            loginUserUseCase: LoginUserUseCase,
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    router,
                    loginUserUseCase,
                )
            }
        }
    }
}