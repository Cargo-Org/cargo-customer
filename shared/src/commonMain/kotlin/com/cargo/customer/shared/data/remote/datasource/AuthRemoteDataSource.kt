package com.cargo.customer.shared.data.remote.datasource

import com.cargo.customer.shared.data.remote.dto.LoginRequestDto
import com.cargo.customer.shared.data.remote.dto.LoginResponseDto
import com.cargo.customer.shared.domain.result.ApiResult
import com.cargo.customer.shared.data.remote.dto.RegisterRequest
import com.cargo.customer.shared.data.remote.dto.RegisterResponse

interface AuthRemoteDataSource {
    suspend fun loginWithEmailAndPassword(loginRequest : LoginRequestDto): ApiResult<LoginResponseDto>
    suspend fun register(request: RegisterRequest): ApiResult<RegisterResponse>

}