package com.cargo.customer.shared.data.repository

import com.cargo.customer.shared.data.local.datasource.AuthLocalDataSource
import com.cargo.customer.shared.data.remote.datasource.AuthRemoteDataSource
import com.cargo.customer.shared.domain.repository.AuthRepository

class AuthRepositoryImp (
    private val remote: AuthRemoteDataSource,
    private val local: AuthLocalDataSource
): AuthRepository {
}