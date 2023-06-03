package com.example.feature_profile_screen_impl.presentation.fragment

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.database.DataBaseRepository
import com.example.feature_profile_screen_api.model.UserProfileModel
import com.example.feature_profile_screen_impl.presentation.di.ProfileRouter
import kotlinx.coroutines.launch

class MainProfileViewModel(
    private val router: ProfileRouter,
): ViewModel() {

    private val _userLiveData = MutableLiveData<UserProfileModel?>(null)
    val userLiveData: LiveData<UserProfileModel?>
        get() = _userLiveData

    fun getUser(repository: DataBaseRepository) {
        viewModelScope.launch {
            val user = repository.findUser()
            _userLiveData.value = UserProfileModel(
                user?.username,
                user?.email,
                user?.dayOfBirth,
                user?.male
            )
        }
    }

    companion object {
        fun provideFactory(
            router: ProfileRouter,
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
               MainProfileViewModel(
                   router,
               )
            }
        }
    }
}