package com.sopt.dive.data.auth.repository

import com.sopt.dive.core.network.model.BaseResponse
import com.sopt.dive.data.auth.dto.request.SignInRequestDto
import com.sopt.dive.data.auth.dto.request.SignUpRequestDto
import com.sopt.dive.data.auth.dto.response.SignInResponseDto
import com.sopt.dive.data.auth.dto.response.SignUpResponseDto
import com.sopt.dive.data.auth.service.AuthService

class AuthRepository(
    private val authService: AuthService
) {
    suspend fun postSignIn(request: SignInRequestDto): Result<BaseResponse<SignInResponseDto>> =
        runCatching {
            authService.postSignIn(request)
        }

    suspend fun postSignUp(request: SignUpRequestDto): Result<BaseResponse<SignUpResponseDto>> =
        runCatching {
            authService.postSignUp(request)
        }
}
