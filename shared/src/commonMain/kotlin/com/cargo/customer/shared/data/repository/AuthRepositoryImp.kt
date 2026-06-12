package com.cargo.customer.shared.data.repository

import com.cargo.customer.shared.data.local.datasource.AuthLocalDataSource
import com.cargo.customer.shared.data.remote.datasource.AuthRemoteDataSource
import com.cargo.customer.shared.data.remote.dto.RegisterRequest
import com.cargo.customer.shared.data.remote.dto.RegisterResponse
import com.cargo.customer.shared.domain.repository.AuthRepository
import com.cargo.customer.shared.domain.result.ApiResult

class AuthRepositoryImp (
    private val remote: AuthRemoteDataSource,
    private val local: AuthLocalDataSource
): AuthRepository {
    override suspend fun register(request: RegisterRequest): ApiResult<RegisterResponse> {
        return remote.register(request)
    }
}