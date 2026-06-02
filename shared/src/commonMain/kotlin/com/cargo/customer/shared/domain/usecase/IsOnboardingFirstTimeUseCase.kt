package com.cargo.customer.shared.domain.usecase

import com.cargo.customer.shared.domain.repository.OnboardingRepository

class IsOnboardingFirstTimeUseCase(
    private val repository: OnboardingRepository
) {
    suspend operator fun invoke() = repository.isFirstTime()
}