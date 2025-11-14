package com.sopt.dive.presentation.signin

import androidx.compose.runtime.Immutable

@Immutable
data class SignInState(
    val userId: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false
)

sealed interface SignInSideEffect{
    data object NavigateToHome: SignInSideEffect
    data object NavigateToSignUp: SignInSideEffect
}
