package com.cargo.customer.shared.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponseDto (
    @SerialName("accessToken")
    val accessToken: String,

    @SerialName("expiresIn")
    val expiresIn: Long,

    @SerialName("refreshToken")
    val refreshToken: String,

    @SerialName("refreshExpiresIn")
    val refreshExpiresIn: Long
)