package com.cargo.customer.shared.data.local.datasource

interface AuthLocalDataSource {
    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)

    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?

    suspend fun clearTokens()
}