package com.cargo.customer.shared.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DocumentDto(
    @SerialName("id")
    val id: String,
    @SerialName("documentType")
    val documentType: Int,
    @SerialName("originalFileName")
    val fileName: String,
    @SerialName("contentType")
    val contentType: String,
    @SerialName("fileSizeBytes")
    val fileSizeBytes: Int,
    @SerialName("reviewStatus")
    val reviewStatus: Int,
    @SerialName("reviewNote")
    val reviewNote: String?,
    @SerialName("uploadedAt")
    val uploadedAt: String,
    @SerialName("reviewedAt")
    val reviewedAt: String?,
    @SerialName("downloadUrl")
    val downloadUrl: String?
)