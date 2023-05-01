package com.example.feature_main_screen_impl.presentation.viewModel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_main_screen_api.model.CardModel
import com.example.feature_main_screen_impl.domain.GetCardUseCase
import kotlinx.coroutines.launch

class CardViewModel(
    private val getCardUseCase: GetCardUseCase
): ViewModel() {

    private val _randomCard = MutableLiveData<CardModel?>(null)
    val randomCard: LiveData<CardModel?>
        get() = _randomCard

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get()  = _error

    fun getRandomCard() {
        viewModelScope.launch {
            try {
                if (!getCardUseCase().name.isNullOrEmpty())
                    _randomCard.value = getCardUseCase()
            }
            catch (error: Throwable) {
                _error.value = error
            }
        }
    }

    companion object {
        fun provideFactory(
            getCardUseCase: GetCardUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CardViewModel(
                    getCardUseCase
                )
            }
        }
    }
}