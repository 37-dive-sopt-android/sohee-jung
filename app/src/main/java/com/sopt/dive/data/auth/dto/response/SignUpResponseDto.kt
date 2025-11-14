package com.sopt.dive.data.auth.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    @SerialName("id")
    val id: Long,
    @SerialName("username")
    val username: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("age")
    val age: Int,
    @SerialName("status")
    val status: String
)
