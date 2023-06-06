package com.example.featureregistrationimpl.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.database.DataBaseRepository
import com.example.database.model.UserLocal
import com.example.feature_registration_api.domain.model.UserModel
import com.example.featureregistrationimpl.domain.LoginUserUseCase
import com.example.featureregistrationimpl.presentation.di.LoginRouter
import com.example.featureregistrationimpl.presentation.utils.getZodiacSign
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


    fun loginUser(context: Context, email: String, password: String, repository: DataBaseRepository) {
        viewModelScope.launch {
            try {
                if (!loginUserUseCase(email, password).username.isNullOrEmpty()) {
                    _userLiveData.value = loginUserUseCase(email, password)
                    repository.addUser(
                        UserLocal(
                            _userLiveData.value?.email.toString(),
                            _userLiveData.value?.username.toString(),
                            _userLiveData.value?.dayOfBirth.toString(),
                            _userLiveData.value?.male,
                            getZodiacSign(_userLiveData.value?.dayOfBirth.toString()),
                            Uri.parse("")
                        )
                    )
                    loginClick()
                }
            }
            catch (error: Throwable) {
                Toast.makeText(context, "Такого пользователя не существует", Toast.LENGTH_SHORT).show()
                Log.e("error", error.toString())
            }
        }
    }

    private fun loginClick() {
        router.openHome()
    }


    fun registerClick() {
        router.openRegister()
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