package com.cargo.customer.shared.data.repository

import com.cargo.customer.shared.data.local.datasource.AuthLocalDataSource
import com.cargo.customer.shared.data.remote.datasource.AuthRemoteDataSource
import com.cargo.customer.shared.data.remote.dto.LoginRequestDto
import com.cargo.customer.shared.data.remote.dto.LoginResponseDto
import com.cargo.customer.shared.data.remote.dto.RegisterRequest
import com.cargo.customer.shared.data.remote.dto.RegisterResponse
import com.cargo.customer.shared.domain.repository.AuthRepository
import com.cargo.customer.shared.domain.result.ApiResult

class AuthRepositoryImp (
    private val remote: AuthRemoteDataSource,
    private val local: AuthLocalDataSource
): AuthRepository {
    override suspend fun loginWithEmailAndPassword(loginRequestDto: LoginRequestDto): ApiResult<LoginResponseDto> {
        val result = remote.loginWithEmailAndPassword(loginRequestDto)
        when (result) {
            is ApiResult.Success -> {
                local.saveAccessToken(result.data.accessToken)
                local.saveRefreshToken(result.data.refreshToken)
            }
            is ApiResult.Error -> { println("LOGIN ERROR") }
        }
        return result
    }
    override suspend fun getAccessToken(): String? {
        return local.getAccessToken()
    }
    override suspend fun clearTokens() {
        local.clearTokens()
    }
    override suspend fun register(request: RegisterRequest): ApiResult<RegisterResponse> {
        return remote.register(request)

    }
}