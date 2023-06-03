package com.example.feature_profile_screen_impl.presentation.fragment

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_profile_screen_impl.presentation.di.ProfileRouter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@Immutable
data class ProfileViewState(
    val email: String = "",
    val username: String = "",
    val male: Boolean = false,
    val icon: String = "",
    val dateOfBirth: String = "",
    val navigateToChangeProfile: Boolean? = null
)

sealed interface ProfileEvent {

    data class OnChangeUser(val email: String, val username: String, val male: Boolean, val icon: String, val dateOfBirth: String): ProfileEvent
    object OnChangeButtonClick: ProfileEvent
    object OnSaveButtonClick: ProfileEvent
}

sealed interface ProfileAction {

    object Navigate: ProfileAction
}

class ProfileViewModel: ViewModel() {

    private val _state = MutableStateFlow<ProfileViewState>(ProfileViewState())
    val state
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<ProfileAction?>()
    val action: SharedFlow<ProfileAction?>
        get() = _action.asSharedFlow()



    fun event(profileEvent: ProfileEvent) {
        when (profileEvent) {
            is ProfileEvent.OnChangeUser -> TODO()
            ProfileEvent.OnChangeButtonClick -> OnChangeButtonClick()
            ProfileEvent.OnSaveButtonClick -> TODO()
        }
    }

    private fun OnChangeButtonClick() {
        viewModelScope.launch {
            _action.emit(ProfileAction.Navigate)
        }
    }

    private fun OnChangeUser() {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(

                )
            )
        }
    }
}