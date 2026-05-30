package com.cargo.customer.shared.domain.repository

import com.cargo.customer.shared.data.remote.dto.LoginRequestDto
import com.cargo.customer.shared.data.remote.dto.LoginResponseDto
import com.cargo.customer.shared.domain.result.ApiResult

interface AuthRepository {
    suspend fun login(loginRequestDto: LoginRequestDto): ApiResult<LoginResponseDto>
}