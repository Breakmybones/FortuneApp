package com.example.feature_main_screen_impl.presentation.viewModel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_main_screen_api.model.CardModel
import com.example.feature_main_screen_impl.domain.GetCardUseCase
import com.example.feature_main_screen_impl.presentation.di.MainRouter
import kotlinx.coroutines.launch

class HomeViewModel(
    private val router: MainRouter,
    private val getCardUseCase: GetCardUseCase
): ViewModel() {

    private val _randomCard = MutableLiveData<CardModel?>(null)
    val randomCard: LiveData<CardModel?>
        get() = _randomCard

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get()  = _error

    fun clickOnRandomCard() {
        viewModelScope.launch {
            try {
                if (!getCardUseCase().suit.isNullOrEmpty()) {
                    _randomCard.value = getCardUseCase()
                    Log.e("card", _randomCard.value.toString())
                }
            }
            catch (error: Throwable) {
                _error.value = error
                Log.e("error", error.toString())
            }
        }

    }

    fun navigateToProfile() {
        router.openProfile()
    }

    fun navigateToFortune() {
        router.openFortune()
    }

    fun navigateToZodiac() {
        router.openZodiac()
    }

    fun navigateToNumbers() {
        router.openNumbers()
    }

    companion object {
        fun provideFactory(
            router: MainRouter,
            getCardUseCase: GetCardUseCase,
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                    router,
                    getCardUseCase,
                )
            }
        }
    }
}
