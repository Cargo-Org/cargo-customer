package com.cargo.customer.shared.data.remote.datasource

import com.cargo.customer.shared.data.local.datastore.TokenStorage
import com.cargo.customer.shared.data.remote.client.NetworkClient

class AuthRemoteDataSourceImpl(
    private val client: NetworkClient,
    private val tokenStorage: TokenStorage
) : AuthRemoteDataSource {
}