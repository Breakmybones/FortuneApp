package com.example.featureregistrationimpl.presentation

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_registration_api.domain.model.UserModel
import com.example.featureregistrationimpl.domain.LoginUserUseCase
import com.example.featureregistrationimpl.domain.RegisterUserUseCase
import com.example.featureregistrationimpl.presentation.di.RegisterRouter
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val router: RegisterRouter,
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _user = MutableLiveData<UserModel?>(null)
    val user: LiveData<UserModel?>
        get() = _user

    fun registerUser(
        username: String?,
        email: String?,
        password: String?,
        dayOfBirth: String?,
        male: Boolean?,
    ) {
        viewModelScope.launch {
            try {
                registerUserUseCase(
                    username = username,
                    email = email,
                    password = password,
                    dayOfBirth = dayOfBirth,
                    male = male,
                )
                if (!loginUserUseCase(email, password).email.toString().isNullOrEmpty())
                    _user.value = loginUserUseCase(email, password)
            }
            catch (error: Throwable) {
                _error.value = error
            }
        }
    }

    companion object {
        fun provideFactory(
            router: RegisterRouter,
            registerUserUseCase: RegisterUserUseCase,
            loginUserUseCase: LoginUserUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RegistrationViewModel(
                    router,
                    registerUserUseCase,
                    loginUserUseCase
                )
            }
        }
    }

}