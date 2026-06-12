package com.cargo.customer.shared.domain.repository

import com.cargo.customer.shared.data.remote.dto.RegisterRequest
import com.cargo.customer.shared.data.remote.dto.RegisterResponse
import com.cargo.customer.shared.domain.result.ApiResult

interface AuthRepository {
    suspend fun register(request: RegisterRequest): ApiResult<RegisterResponse>
}