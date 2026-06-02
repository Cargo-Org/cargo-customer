package com.cargo.customer.shared.domain.usecase.auth

import com.cargo.customer.shared.data.remote.dto.RegisterRequest
import com.cargo.customer.shared.data.remote.dto.RegisterResponse
import com.cargo.customer.shared.domain.repository.AuthRepository
import com.cargo.customer.shared.domain.result.ApiResult

class RegisterUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        request: RegisterRequest
    ): ApiResult<RegisterResponse> {
        return authRepository.register(request)
    }
}