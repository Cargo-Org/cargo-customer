package com.cargo.customer.shared.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubmitDocumentRequest(
    @SerialName("documentType")
    val documentType: Int,
    @SerialName("objectKey")
    val objectKey: String,
    @SerialName("originalFileName")
    val originalFileName: String,
    @SerialName("contentType")
    val contentType: String,
    @SerialName("fileSizeBytes")
    val fileSizeBytes: String
)