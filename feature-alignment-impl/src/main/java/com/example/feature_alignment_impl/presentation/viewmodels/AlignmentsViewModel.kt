package com.example.feature_alignment_impl.presentation.viewmodels

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_alignment_api.domain.models.CardsListModel
import com.example.feature_alignment_api.domain.models.CardsModel
import com.example.feature_alignment_impl.domain.*
import com.example.feature_alignment_impl.presentation.routers.SelectCardsRouter
import kotlinx.coroutines.launch
import timber.log.Timber

class AlignmentsViewModel(
    private val router: SelectCardsRouter,
    private val alignmentsUseCase: AlignmentsUseCase

):  ViewModel() {

    private val _cards = MutableLiveData<Result<CardsListModel>>()
    val cards: LiveData<Result<CardsListModel>>
        get() = _cards

    private val _card = MutableLiveData<Result<CardsModel>>()
    val card: LiveData<Result<CardsModel>>
        get() = _card

    private var _error: MutableLiveData<Exception> = MutableLiveData()

    fun getCards() {
        viewModelScope.launch {
            try {
                val cards = alignmentsUseCase(30)
                _cards.value = Result.success(cards)
            } catch (ex: Exception) {
                _cards.value = Result.failure(ex)
                _error.value = ex
                Log.e("error", ex.toString())
//                Timber.e("Get Cards error")
            }
        }
    }

    fun openAlignmentFragment(listId: ArrayList<Long>, bundle: Bundle){
        router.openAlignmentFragment(listId, bundle)
    }

    companion object{
        fun provideFactory(
            router: SelectCardsRouter,
            alignmentsUseCase: AlignmentsUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AlignmentsViewModel(
                    router,
                    alignmentsUseCase
                )
            }
        }
    }
}