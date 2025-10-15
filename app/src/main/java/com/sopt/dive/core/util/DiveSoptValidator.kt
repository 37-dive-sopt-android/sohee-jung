package com.sopt.dive.core.util

import com.sopt.dive.core.util.DiveSoptValidator.isMbtiFormat
import com.sopt.dive.core.util.DiveSoptValidator.isNicknameFormat
import com.sopt.dive.core.util.DiveSoptValidator.isPasswordFormat
import com.sopt.dive.core.util.DiveSoptValidator.isUserIdFormat

object DiveSoptValidator {
    private val userIdPattern = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]{6,12}$".toRegex()
    private val passwordPattern = "^[A-Za-z\\d~!@#$%^&*]{8,12}$".toRegex()
    private val nicknamePattern = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]\\S+$".toRegex()
    private val mbtiPattern = "^[IE][NS][FT][JP]$".toRegex(option = RegexOption.IGNORE_CASE)

    fun isUserIdFormat(input: String): Boolean {
        return userIdPattern.matches(input)
    }

    fun isPasswordFormat(input: String): Boolean {
        return passwordPattern.matches(input)
    }

    fun isNicknameFormat(input: String): Boolean {
        return nicknamePattern.matches(input)
    }

    fun isMbtiFormat(input: String): Boolean {
        return mbtiPattern.matches(input)
    }
}

fun validateMessage(
    userId: String,
    password: String,
    nickname: String,
    mbti: String
): List<String> {
    val errors = mutableListOf<String>()

    if (!isUserIdFormat(userId)) {
        errors += "ID 형식이 올바르지 않습니다. (영문/숫자/한글 6~12자)"
    }
    if (!isPasswordFormat(password)) {
        errors += "비밀번호 형식이 올바르지 않습니다. (영문/숫자/특수문자 조합 8~16자)"
    }
    if (!isNicknameFormat(nickname)) {
        errors += "닉네임 형식이 올바르지 않습니다. (공백 불가)"
    }
    if (!isMbtiFormat(mbti)) {
        errors += "MBTI 형식이 올바르지 않습니다. (예: INTJ)"
    }
    return errors
}
