package com.cargo.customer.shared.domain.usecase

import com.cargo.customer.shared.domain.repository.AuthRepository

class IsLoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return authRepository.getAccessToken() != null
    }
}