package com.cargo.customer.shared.data.repository

import com.cargo.customer.shared.data.local.datasource.OnboardingDataSource
import com.cargo.customer.shared.domain.repository.OnboardingRepository

class OnboardingRepositoryImp(
    private val dataSource: OnboardingDataSource
): OnboardingRepository{

    override suspend fun setFirstTimeStatus() {
        dataSource.setFirstTimeStatus()
    }

    override suspend fun isFirstTime(): Boolean {
       return dataSource.isFirstTime()
    }
}