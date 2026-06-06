package com.cargo.customer.shared.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadUrlRequest(
    @SerialName("documentType")
    val documentType: Int,
    @SerialName("contentType")
    val contentType: String
)