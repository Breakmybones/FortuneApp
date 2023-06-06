package com.example.feature_signs_impl.presentation.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_signs_api.model.ListResponse
import com.example.feature_signs_impl.domain.ZodiacUseCase
import com.example.feature_signs_impl.presentation.routers.ZodiacRouter
import kotlinx.coroutines.launch

class ZodiacViewModel(
    private val router: ZodiacRouter,
    private val zodiacUseCase: ZodiacUseCase
):  ViewModel() {

    private val _zodiac = MutableLiveData<Result<ListResponse>>()
    val zodiac: LiveData<Result<ListResponse>>
        get() = _zodiac


    private var _error: MutableLiveData<Exception> = MutableLiveData()

    fun getZodiac(name: String){
        viewModelScope.launch {
            try {
                val zodiac = zodiacUseCase(name)
                _zodiac.value = Result.success(zodiac)
            } catch (ex: Exception) {
                _zodiac.value = Result.failure(ex)
                _error.value = ex
                Log.e("error", ex.toString())
            }
        }
    }

//    fun openDetailFragment(bundle: Bundle){
//        router.openDetailFragment(bundle)
//    }

    companion object{
        fun provideFactory(
            router: ZodiacRouter,
            zodiacUseCase: ZodiacUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ZodiacViewModel(
                    router,
                    zodiacUseCase
                )
            }
        }
    }
}