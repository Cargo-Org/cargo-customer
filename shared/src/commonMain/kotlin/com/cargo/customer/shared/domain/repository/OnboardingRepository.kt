package com.cargo.customer.shared.domain.repository

interface OnboardingRepository {
    suspend fun setFirstTimeStatus()
    suspend fun isFirstTime(): Boolean
}