package com.sopt.dive.presentation.signup

import androidx.compose.runtime.Immutable

@Immutable
data class SignUpState(
    val userId: String = "",
    val password: String = "",
    val name: String = "",
    val email: String = "",
    val age: Int = 0,
    val isPasswordVisible: Boolean = false
)

sealed interface SignUpSideEffect{
    data object NavigateToSignIn : SignUpSideEffect
}
