package com.sopt.dive.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.auth.dto.request.SignUpRequestDto
import com.sopt.dive.data.auth.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpState())
    // asStateFlow
    val uiState: StateFlow<SignUpState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    // sharedFlow
    val sideEffect: SharedFlow<SignUpSideEffect> = _sideEffect.asSharedFlow()

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

    fun updateName(name: String) {
        _uiState.update {
            it.copy(name = name)
        }
    }

    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun updateAge(age: String) {
        val age = age.toIntOrNull() ?: return
        _uiState.update {
            it.copy(age = age)
        }
    }

    fun onIconClicked() {
        _uiState.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    fun onSignUpClicked() {
        val username = uiState.value.username
        val password = uiState.value.password
        val name = uiState.value.name
        val email = uiState.value.email
        val age = uiState.value.age

        // viewModelScope 사용
        viewModelScope.launch {
            val result = authRepository.postSignUp(
                SignUpRequestDto(
                    username = username,
                    password = password,
                    name = name,
                    email = email,
                    age = age!!
                )
            )

            if (result.isSuccess) {
                _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
            } else {
                _sideEffect.emit(SignUpSideEffect.ShowToast("회원가입에 실패했습니다."))
            }
        }
    }
}
