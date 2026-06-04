package com.cargo.customer.shared.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadUrlDto(
    @SerialName("uploadUrl")
    val uploadUrl: String,
    @SerialName("objectKey")
    val objectKey: String
)