package com.cargo.customer.shared.data.mapper

import com.cargo.customer.shared.data.remote.dto.DocumentDto
import com.cargo.customer.shared.domain.model.DocumentModel

fun DocumentDto.toDomain()=
    DocumentModel(
        id = id,
        documentType = documentType,
        fileName = fileName,
        contentType = contentType,
        fileSizeBytes = fileSizeBytes,
        reviewStatus = reviewStatus,
        reviewNote = reviewNote
    )