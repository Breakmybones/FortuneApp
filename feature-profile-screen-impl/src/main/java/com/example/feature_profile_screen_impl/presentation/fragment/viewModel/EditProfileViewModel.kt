package com.example.feature_profile_screen_impl.presentation.fragment.viewModel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.feature_profile_screen_api.model.UserProfileModel
import com.example.feature_profile_screen_impl.domain.EditUserUseCase
import com.example.feature_profile_screen_impl.domain.GetUserUseCase
import com.example.feature_profile_screen_impl.presentation.di.EditProfileRouter
import com.example.feature_profile_screen_impl.presentation.di.ProfileRouter
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val editUserUseCase: EditUserUseCase,
    private val router: ProfileRouter
): ViewModel() {

    private val _userLiveData = MutableLiveData<UserProfileModel?>(null)
    val userLiveData: LiveData<UserProfileModel?>
        get() = _userLiveData

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    fun editUser(
        username: String?,
        email: String?,
        dayOfBirth: String?,
        male: Boolean?,
    ) {
        viewModelScope.launch {
            try {
                if (getUserUseCase(email).username.toString().isNotEmpty()) {
                    _userLiveData.value = getUserUseCase(email)
                    editUserUseCase(
                        username = username,
                        email = userLiveData.value?.email,
                        dayOfBirth = dayOfBirth,
                        male = male,
                    )
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
            getUserUseCase: GetUserUseCase,
            editUserUseCase: EditUserUseCase,
            router: ProfileRouter
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditProfileViewModel(
                    getUserUseCase,
                    editUserUseCase,
                    router
                )
            }
        }
    }
}