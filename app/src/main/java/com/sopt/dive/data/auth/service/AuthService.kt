package com.sopt.dive.data.auth.service

import com.sopt.dive.core.network.model.BaseResponse
import com.sopt.dive.data.auth.dto.request.SignInRequestDto
import com.sopt.dive.data.auth.dto.request.SignUpRequestDto
import com.sopt.dive.data.auth.dto.response.SignInResponseDto
import com.sopt.dive.data.auth.dto.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/login")
    suspend fun postSignIn(
        @Body request: SignInRequestDto
    ): BaseResponse<SignInResponseDto>

    @POST("/api/v1/users")
    suspend fun postSignUp(
        @Body request: SignUpRequestDto
    ): BaseResponse<SignUpResponseDto>
}
