package com.cargo.customer.shared.data.remote.dto

import kotlinx.serialization.Serializable

sealed interface ErrorResponse

@Serializable
data class ApiErrorResponse(
    val type: String,
    val title: String,
    val status: Int,
    val detail: String,
): ErrorResponse

@Serializable
data class ValidationErrorResponse(
    val status: Int,
    val errors: Map<String, List<String>>,
) : ErrorResponse