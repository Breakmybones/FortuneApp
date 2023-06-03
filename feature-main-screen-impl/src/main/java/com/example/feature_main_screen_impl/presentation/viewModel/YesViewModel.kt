package com.example.feature_main_screen_impl.presentation.viewModel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_main_screen_impl.domain.GetCookieUseCase
import com.example.feature_main_screen_impl.domain.GetYesUseCase
import kotlinx.coroutines.launch

class YesViewModel(
    private val getYesUseCase: GetYesUseCase
): ViewModel() {

    private val _randomYes = MutableLiveData<String?>(null)
    val randomYes: LiveData<String?>
        get() = _randomYes

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get()  = _error

    fun getRandomYes() {
        viewModelScope.launch {
            try {
                if (!getYesUseCase().isNullOrEmpty()) {
                    _randomYes.value = getYesUseCase()
                    Log.e("digit", _randomYes.value.toString())
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
            getYesUseCase: GetYesUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                YesViewModel(
                    getYesUseCase,
                )
            }
        }
    }

}