package com.cargo.customer.shared.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto (
    @SerialName("customerId")
    val customerID: String,

    @SerialName("fullName")
    val fullName: String,

    @SerialName("accessToken")
    val accessToken: String,

    @SerialName("refreshToken")
    val refreshToken: String,

    @SerialName("accessTokenExpiresIn")
    val accessTokenExpiresIn: Long,

    @SerialName("refreshTokenExpiresIn")
    val refreshTokenExpiresIn: Long
)
