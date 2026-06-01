package com.cargo.customer.shared.data.remote.datasource

import com.cargo.customer.shared.data.local.datastore.TokenStorage
import com.cargo.customer.shared.data.remote.client.NetworkClient
import com.cargo.customer.shared.data.remote.dto.LoginRequestDto
import com.cargo.customer.shared.data.remote.dto.LoginResponseDto
import com.cargo.customer.shared.data.remote.util.ApiConstants
import com.cargo.customer.shared.data.remote.util.safeApiCall
import com.cargo.customer.shared.domain.result.ApiResult
import io.ktor.client.request.setBody

class AuthRemoteDataSourceImpl(
    private val client: NetworkClient,
) : AuthRemoteDataSource {
    override suspend fun loginWithEmailAndPassword(loginRequest: LoginRequestDto): ApiResult<LoginResponseDto> {
        return safeApiCall<LoginResponseDto> {
            client.post(ApiConstants.LOGIN_ENDPOINT) {
                setBody(loginRequest)
            }
        }
    }
}