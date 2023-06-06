package com.example.feature_alignment_impl.presentation.viewmodels

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_alignment_api.domain.models.CardsListModel
import com.example.feature_alignment_api.domain.models.CardsModel
import com.example.feature_alignment_impl.domain.AlignmentsUseCase
import com.example.feature_alignment_impl.domain.CardByIdUseCase
import com.example.feature_alignment_impl.domain.CardsByIdUseCase
import com.example.feature_alignment_impl.presentation.routers.AlignmentRouter
import com.example.feature_alignment_impl.presentation.routers.SelectCardsRouter
import kotlinx.coroutines.launch
import timber.log.Timber

class AlignmentViewModel(
    private val router: AlignmentRouter,
    private val cardsByIdUseCase: CardsByIdUseCase

):  ViewModel() {

    private val _cards = MutableLiveData<Result<CardsListModel>>()
    val cards: LiveData<Result<CardsListModel>>
        get() = _cards

    private val _card = MutableLiveData<Result<CardsModel>>()
    val card: LiveData<Result<CardsModel>>
        get() = _card

    private var _error: MutableLiveData<Exception> = MutableLiveData()

    fun getCardsById(listId: String){
        viewModelScope.launch {
            try {
                val cards = cardsByIdUseCase(listId)
                _cards.value = Result.success(cards)
            } catch (ex: Exception) {
                _cards.value = Result.failure(ex)
                _error.value = ex
                Log.e("error", ex.toString())
            }
        }
    }

    fun openDetailFragment(bundle: Bundle){
        router.openDetailFragment(bundle)
    }

    companion object{
        fun provideFactory(
            router: AlignmentRouter,
            cardsByIdUseCase: CardsByIdUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AlignmentViewModel(
                    router,
                    cardsByIdUseCase
                )
            }
        }
    }
}