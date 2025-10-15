package com.sopt.dive.core.util

object DiveSoptValidator {
    private val userIdPattern = "^{6,12}$".toRegex()
    private val passwordPattern = "^[a-zA-Z0-9]{8,12}$".toRegex()
    private val nicknamePattern = "^\\S+$".toRegex()
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
