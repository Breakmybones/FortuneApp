package com.example.feature_main_screen_impl.presentation.viewModel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_main_screen_impl.domain.GetCookieUseCase
import com.example.feature_main_screen_impl.domain.GetDigitUseCase
import kotlinx.coroutines.launch

class CookieViewModel(
    private val getCookieUseCase: GetCookieUseCase
): ViewModel() {

    private val _randomCookie = MutableLiveData<String?>(null)
    val randomCookie: LiveData<String?>
        get() = _randomCookie

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get()  = _error

    fun getRandomCookie() {
        viewModelScope.launch {
            try {
                if (!getCookieUseCase().isNullOrEmpty()) {
                    _randomCookie.value = getCookieUseCase()
                    Log.e("digit", _randomCookie.value.toString())
                }
            }
            catch (error: Throwable) {
                _error.value = error
                Log.e("error", error.toString())
            }
        }
    }

    companion object {
        fun provideFactory(
            getCookieUseCase: GetCookieUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CookieViewModel(
                    getCookieUseCase,
                )
            }
        }
    }
}