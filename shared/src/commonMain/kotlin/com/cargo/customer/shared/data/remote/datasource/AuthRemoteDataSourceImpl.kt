package com.cargo.customer.shared.data.remote.datasource

import com.cargo.customer.shared.data.local.datastore.TokenStorage
import com.cargo.customer.shared.data.remote.client.NetworkClient
import com.cargo.customer.shared.data.remote.dto.RegisterRequest
import com.cargo.customer.shared.data.remote.dto.RegisterResponse
import com.cargo.customer.shared.data.remote.util.ApiConstants
import com.cargo.customer.shared.data.remote.util.safeApiCall
import com.cargo.customer.shared.domain.result.ApiResult
import io.ktor.client.request.setBody

class AuthRemoteDataSourceImpl(
    private val client: NetworkClient,
    private val tokenStorage: TokenStorage
) : AuthRemoteDataSource {
    override suspend fun register(request: RegisterRequest): ApiResult<RegisterResponse> {
        return safeApiCall {
            client.post(ApiConstants.REGISTER_ENDPOINT) {
                setBody(request)
            }
        }
    }
}