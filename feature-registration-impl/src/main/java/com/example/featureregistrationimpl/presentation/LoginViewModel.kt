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
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {

    private val _user = MutableLiveData<UserModel?>(null)
    val user: LiveData<UserModel?>
        get() = _user

    private val _navigation = SingleLiveEvent<String?>()
    val navigation: SingleLiveEvent<String?>
        get() = _navigation

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get()  = _error


    fun loginUser(context: Context, login: String, password: String) {
        viewModelScope.launch {
            try {
                if (!loginUserUseCase(login, password).login.isNullOrEmpty())
                    _user.value = loginUserUseCase(login, password)
                    router.openHome(context)
            }
            catch (error: Throwable) {
                _error.value = error
            }
        }
    }

    fun registerClick() {
        Log.e("trash", "content")
        router.openRegister()
    }

    companion object {
        fun provideFactory(
            router: LoginRouter,
            loginUserUseCase: LoginUserUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    router,
                    loginUserUseCase
                )
            }
        }
    }
}