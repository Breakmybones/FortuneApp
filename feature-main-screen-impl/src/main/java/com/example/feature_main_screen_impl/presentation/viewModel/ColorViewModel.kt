package com.example.feature_main_screen_impl.presentation.viewModel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_main_screen_impl.domain.GetColorUseCase
import com.example.feature_main_screen_impl.domain.GetDigitUseCase
import kotlinx.coroutines.launch

class ColorViewModel(
    private val getColorUseCase: GetColorUseCase
): ViewModel() {

    private val _randomColor = MutableLiveData<Map<String, String>?>(null)
    val randomColor: LiveData<Map<String, String>?>
        get() = _randomColor

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get()  = _error

    fun getRandomColor() {
        viewModelScope.launch {
            try {
                if (!getColorUseCase().isNullOrEmpty()) {
                    _randomColor.value = getColorUseCase()
                    Log.e("color", _randomColor.value.toString())
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