package com.sopt.dive.domain

import com.sopt.dive.core.util.validateErrorMessage
import com.sopt.dive.data.UserInfo
import com.sopt.dive.data.UserPrefs

sealed interface Result {
    data object Success : Result
    data class Failure(val message: String) : Result
}

suspend fun CheckSignUpResult(
    prefs: UserPrefs,
    userId: String,
    password: String,
    nickname: String,
    mbti: String
): Result {
    val error =
        validateErrorMessage(userId = userId, password = password, nickname = nickname, mbti = mbti)
    if (error != null) return Result.Failure(error)

    prefs.saveProfile(
        profile = UserInfo(
            userId = userId,
            password = password,
            nickname = nickname,
            mbti = mbti,
            isLoggedIn = false
        )
    )

    return Result.Success
}
