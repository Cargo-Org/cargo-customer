package com.cargo.customer.shared.data.remote.datasource

import com.cargo.customer.shared.data.remote.dto.LoginResponseDto
import com.cargo.customer.shared.data.remote.dto.RefreshTokenResponseDto

interface AuthRemoteDataSource {
    suspend fun login(
        email: String,
        password: String
    ): LoginResponseDto

    suspend fun refreshToken(
        refreshToken: String
    ): RefreshTokenResponseDto
}