package com.sopt.dive.data

import androidx.compose.runtime.Immutable

@Immutable
data class UserInfo(
    val userId: String,
    val password: String,
    val nickname: String,
    val mbti: String,
    val isLoggedIn: Boolean
)
