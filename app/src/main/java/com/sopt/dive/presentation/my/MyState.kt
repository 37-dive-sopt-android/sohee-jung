package com.sopt.dive.presentation.my

import androidx.compose.runtime.Immutable

@Immutable
data class MyState(
    val id: Long = 0L,
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val age: Int = 0
)

sealed interface MySideEffect {
    data object NavigateToSignIn : MySideEffect
}
