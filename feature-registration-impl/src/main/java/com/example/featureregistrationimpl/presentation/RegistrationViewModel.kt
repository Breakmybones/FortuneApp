package com.example.featureregistrationimpl.presentation

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_registration_api.domain.model.UserModel
import com.example.featureregistrationimpl.domain.LoginUserUseCase
import com.example.featureregistrationimpl.domain.RegisterUserUseCase
import kotlinx.coroutines.launch
import java.time.Instant

class RegistrationViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {

    private val _register = MutableLiveData<UserModel?>(null)
    val register: LiveData<UserModel?>
        get() = _register

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _user = MutableLiveData<UserModel?>(null)
    val user: LiveData<UserModel?>
        get() = _user

    private val _navigation = SingleLiveEvent<UserModel?>()
    val navigation: SingleLiveEvent<UserModel?>
        get() = _navigation

    fun registerUser(
        username: String?,
        login: String?,
        password: String?,
        dayOfBirth: Instant?,
        male: Boolean?,
        icon: String?
    ) {
        viewModelScope.launch {
            try {
                registerUserUseCase(
                    username = username,
                    login = login,
                    password = password,
                    dayOfBirth = dayOfBirth,
                    male = male,
                    icon = icon
                )
                if (!loginUserUseCase(login, password).login.toString().isNullOrEmpty())
                    _navigation.value = loginUserUseCase(login, password)
            }
            catch (error: Throwable) {
                _error.value = error
            }
        }
    }

    companion object {
        fun provideFactory(
            registerUserUseCase: RegisterUserUseCase,
            loginUserUseCase: LoginUserUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RegistrationViewModel(
                    registerUserUseCase,
                    loginUserUseCase
                )
            }
        }
    }

}