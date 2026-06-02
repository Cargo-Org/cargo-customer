package com.cargo.customer.shared.data.remote.datasource

import com.cargo.customer.shared.data.remote.dto.RegisterRequest
import com.cargo.customer.shared.data.remote.dto.RegisterResponse
import com.cargo.customer.shared.domain.result.ApiResult

interface AuthRemoteDataSource {
    suspend fun register(request: RegisterRequest): ApiResult<RegisterResponse>
}