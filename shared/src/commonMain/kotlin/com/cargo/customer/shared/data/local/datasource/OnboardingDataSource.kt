package com.cargo.customer.shared.data.local.datasource

interface OnboardingDataSource {
    suspend fun setFirstTimeStatus()
    suspend fun isFirstTime(): Boolean
}