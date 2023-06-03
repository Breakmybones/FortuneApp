package com.example.feature_main_screen_impl.presentation.viewModel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_main_screen_impl.domain.GetDigitUseCase
import kotlinx.coroutines.launch

class DigitViewModel(
    private val getDigitUseCase: GetDigitUseCase
): ViewModel() {

    private val _randomDigit = MutableLiveData<Map<String, String>?>(null)
    val randomDigit: LiveData<Map<String, String>?>
        get() = _randomDigit

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get()  = _error

    fun getRandomDigit() {
        viewModelScope.launch {
            try {
                if (!getDigitUseCase().isNullOrEmpty()) {
                    _randomDigit.value = getDigitUseCase()
                    Log.e("digit", _randomDigit.value.toString())
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
            getDigitUseCase: GetDigitUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                DigitViewModel(
                    getDigitUseCase,
                )
            }
        }
    }
}