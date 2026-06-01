package com.cargo.customer.shared.data.local.datasource

import com.cargo.customer.shared.data.local.datastore.TokenStorage

class AuthLocalDataSourceImp (
    private val tokenStorage: TokenStorage
) : AuthLocalDataSource {
    override suspend fun saveAccessToken(token: String) {
        tokenStorage.saveAccessToken(token)
    }

    override suspend fun saveRefreshToken(token: String) {
        tokenStorage.saveRefreshToken(token)
    }

    override suspend fun getAccessToken(): String? {
        return tokenStorage.getAccessToken()
    }

    override suspend fun getRefreshToken(): String? {
        return getRefreshToken()
    }

    override suspend fun clearTokens() {
        tokenStorage.clearTokens()
    }
}