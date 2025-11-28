package com.sopt.dive.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.UserPrefs
import com.sopt.dive.data.auth.dto.request.SignInRequestDto
import com.sopt.dive.data.auth.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository,
    private val userPrefs: UserPrefs
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInState())
    // asStateFlow
    val uiState: StateFlow<SignInState> = _uiState.asStateFlow()
    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    // sharedFlow
    val sideEffect: SharedFlow<SignInSideEffect> = _sideEffect.asSharedFlow()

    fun updateUserId(username: String) {
        _uiState.update {
            it.copy(username = username)
        }
    }

    fun updatePassword(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun onIconClicked() {
        _uiState.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    fun onSignInClicked() {
        val username = uiState.value.username
        val password = uiState.value.password

        // viewModelScope 사용
        viewModelScope.launch {
            val result = authRepository.postSignIn(
                SignInRequestDto(
                    username = username,
                    password = password
                )
            )

            result.onSuccess { response ->
                val userId = response.data.userId

                userPrefs.setUserId(userId)
                userPrefs.setLoggedIn(true)

                _sideEffect.emit(SignInSideEffect.NavigateToHome)
            }.onFailure {

            }
        }
    }

    fun onSignUpClicked() {
        viewModelScope.launch {
            _sideEffect.emit(SignInSideEffect.NavigateToSignUp)
        }
    }
}
