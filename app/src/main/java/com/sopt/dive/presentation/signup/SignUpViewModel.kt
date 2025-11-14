package com.sopt.dive.presentation.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpState())
    val uiState: StateFlow<SignUpState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect: SharedFlow<SignUpSideEffect> = _sideEffect.asSharedFlow()


    fun updateUserId(userId: String) {
        _uiState.update {
            it.copy(userId = userId)
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

    fun onIconClicked(){
        _uiState.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    fun onSignUpClicked(){

    }

//    {
//        scope.launch {
//            when (val r= CheckSignUpResult(prefs = prefs, userId = userId, password = password, nickname = nickname, mbti = mbti)) {
//                is com.sopt.dive.domain.Result.Success -> {
//                    Toast.makeText(context, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
//                    onSignUpClick()
//                }
//                is Result.Failure -> {
//                    snackbar.showSnackbar(r.message)
//                }
//            }
//        }
//    }
}
