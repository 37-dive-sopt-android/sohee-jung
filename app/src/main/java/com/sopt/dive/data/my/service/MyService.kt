package com.sopt.dive.data.my.service

import com.sopt.dive.core.network.model.BaseResponse
import com.sopt.dive.data.my.dto.response.MyResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MyService {
    @GET("/api/v1/users/{id}")
    suspend fun getUser(
        @Path("id") id: Long
    ): BaseResponse<MyResponseDto>
}
