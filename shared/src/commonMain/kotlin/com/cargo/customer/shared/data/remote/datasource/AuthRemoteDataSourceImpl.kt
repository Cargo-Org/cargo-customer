package com.cargo.customer.shared.data.remote.datasource

import com.cargo.customer.shared.data.local.datastore.TokenStorage
import com.cargo.customer.shared.data.remote.client.NetworkClient
import com.cargo.customer.shared.data.remote.dto.LoginResponseDto
import com.cargo.customer.shared.data.remote.dto.RefreshTokenResponseDto
import com.cargo.customer.shared.data.remote.util.ApiConstants
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRemoteDataSourceImpl(
    private val client: NetworkClient,
) : AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): LoginResponseDto {
        return client.post(ApiConstants.LOGIN_ENDPOINT){
            contentType(ContentType.Application.Json)
            setBody(
                mapOf(
                    "email" to email,
                    "password" to password
                )
            )
        }.body()
    }

    override suspend fun refreshToken(refreshToken: String): RefreshTokenResponseDto {
        return client.post(ApiConstants.REFRESH_TOKEN_ENDPOINT){
            contentType(ContentType.Application.Json)
            setBody(
                mapOf(
                    "refreshToken" to refreshToken,
                )
            )
        }.body()
    }
}