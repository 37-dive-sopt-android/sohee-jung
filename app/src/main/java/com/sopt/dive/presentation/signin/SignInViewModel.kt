package com.sopt.dive.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.presentation.signup.SignUpSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignInState())
    val uiState: StateFlow<SignInState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    val sideEffect: SharedFlow<SignInSideEffect> = _sideEffect.asSharedFlow()

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

    fun onIconClicked(){
        _uiState.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    fun onSignInClicked(){

    }
//    {
//        scope.launch {
//            if (!userId.isBlank() && !password.isBlank() && userId == profile.userId && password == profile.password) {
//                prefs.setLoggedIn(isLoggedIn = true)
//                onSignInClick()
//            } else {
//                snackbar.showSnackbar("로그인에 실패했습니다.")
//            }
//        }


    fun onSignUpClicked(){
        viewModelScope.launch {
            _sideEffect.emit(SignInSideEffect.NavigateToSignUp)
        }
    }
}
