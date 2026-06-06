package com.cargo.customer.shared.domain.usecase

import com.cargo.customer.shared.domain.repository.AuthRepository

class GetUploadUrlUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(documentType:Int,contentType:String) = authRepository.getUploadUrl(documentType,contentType)
}