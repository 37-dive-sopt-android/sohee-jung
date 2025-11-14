package com.sopt.dive.data.my.repository

import com.sopt.dive.core.network.model.BaseResponse
import com.sopt.dive.data.my.dto.response.MyResponseDto
import com.sopt.dive.data.my.service.MyService

class MyRepository(
    private val myService: MyService
) {
    suspend fun getUser(id: Long): Result<BaseResponse<MyResponseDto>> = runCatching {
        myService.getUser(id)
    }
}
