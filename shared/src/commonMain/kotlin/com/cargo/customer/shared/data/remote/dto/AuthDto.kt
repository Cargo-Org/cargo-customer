package com.cargo.customer.shared.data.remote.dto

import kotlinx.serialization.Serializable
@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
)
@Serializable
data class RegisterResponse(
    val customerId: String? = null,
)