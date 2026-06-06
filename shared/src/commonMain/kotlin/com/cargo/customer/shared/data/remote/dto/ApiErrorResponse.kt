package com.cargo.customer.shared.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ErrorResponse

@Serializable
data class ApiErrorResponse(
    @SerialName("type")
    val type: String,
    @SerialName("title")
    val title: String,
    @SerialName("status")
    val status: Int,
    @SerialName("detail")
    val detail: String,
): ErrorResponse

@Serializable
data class ValidationErrorResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("errors")
    val errors: Map<String, List<String>>,
) : ErrorResponse