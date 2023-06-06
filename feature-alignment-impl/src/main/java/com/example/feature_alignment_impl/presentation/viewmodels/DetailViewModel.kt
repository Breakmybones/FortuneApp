package com.example.feature_alignment_impl.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_alignment_api.domain.models.CardsListModel
import com.example.feature_alignment_api.domain.models.CardsModel
import com.example.feature_alignment_impl.domain.AlignmentsUseCase
import com.example.feature_alignment_impl.domain.CardByIdUseCase
import com.example.feature_alignment_impl.presentation.routers.SelectCardsRouter
import kotlinx.coroutines.launch

class DetailViewModel(
    private val cardByIdUseCase: CardByIdUseCase

):  ViewModel() {

    private val _card = MutableLiveData<Result<CardsModel>>()
    val card: LiveData<Result<CardsModel>>
        get() = _card

    private var _error: MutableLiveData<Exception> = MutableLiveData()

    fun getCardById(id: Long) {
        viewModelScope.launch {
            try {
                val cards = cardByIdUseCase(id)
                _card.value = Result.success(cards)
            } catch (ex: Exception) {
                _card.value = Result.failure(ex)
                _error.value = ex
                Log.e("error", ex.toString())
//                Timber.e("Get Cards error")
            }
        }
    }

    companion object {
        fun provideFactory(
            cardByIdUseCase: CardByIdUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                DetailViewModel(
                    cardByIdUseCase
                )
            }
        }
    }
}