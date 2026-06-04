package com.cargo.customer.shared.domain.usecase

import com.cargo.customer.shared.domain.repository.AuthRepository

class UploadDocumentUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        url : String,
        bytes: ByteArray
    ) = repository.uploadImageToUrl(url, bytes)
}