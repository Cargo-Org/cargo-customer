package com.cargo.customer.shared.domain.repository

import com.cargo.customer.shared.data.remote.dto.LoginRequestDto
import com.cargo.customer.shared.data.remote.dto.LoginResponseDto
import com.cargo.customer.shared.domain.result.ApiResult

interface AuthRepository {
    suspend fun loginWithEmailAndPassword(loginRequestDto: LoginRequestDto): ApiResult<LoginResponseDto>
    suspend fun getAccessToken(): String?
    suspend fun clearTokens()
}