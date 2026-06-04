package com.cargo.customer.shared.domain.model

data class DocumentModel(
    val id: String,
    val documentType: Int,
    val fileName: String,
    val contentType: String,
    val fileSizeBytes: Int,
    val reviewStatus: Int,
    val reviewNote: String?,
)