package com.cargo.customer.shared.data.mapper

import com.cargo.customer.shared.data.remote.dto.UploadUrlDto
import com.cargo.customer.shared.domain.model.UploadUrlModel

fun UploadUrlDto.toDomain() =
    UploadUrlModel(
        uploadUrl = uploadUrl,
        objectKey = objectKey
    )