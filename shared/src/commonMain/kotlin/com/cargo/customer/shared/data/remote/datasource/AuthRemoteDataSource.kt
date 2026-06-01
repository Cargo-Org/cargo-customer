package com.cargo.customer.shared.data.remote.datasource

import com.cargo.customer.shared.data.remote.dto.LoginRequestDto
import com.cargo.customer.shared.data.remote.dto.LoginResponseDto
import com.cargo.customer.shared.domain.result.ApiResult

interface AuthRemoteDataSource {
    suspend fun loginWithEmailAndPassword(
        loginRequest : LoginRequestDto
    ): ApiResult<LoginResponseDto>
}