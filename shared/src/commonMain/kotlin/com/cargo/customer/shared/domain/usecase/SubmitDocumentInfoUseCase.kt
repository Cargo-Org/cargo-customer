package com.cargo.customer.shared.domain.usecase

import com.cargo.customer.shared.domain.repository.AuthRepository

class SubmitDocumentInfoUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        documentType: Int,
        fileName: String,
        key: String,
        contentType: String,
        fileSizeInByte: String
    ){
        repository.submitDocumentData(documentType, fileName, key, contentType, fileSizeInByte)
    }
}