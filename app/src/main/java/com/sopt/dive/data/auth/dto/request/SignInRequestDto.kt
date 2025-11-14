package com.sopt.dive.data.auth.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)
