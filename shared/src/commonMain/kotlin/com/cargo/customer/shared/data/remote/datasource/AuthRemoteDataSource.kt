package com.cargo.customer.shared.data.remote.datasource

import com.cargo.customer.shared.data.remote.dto.DocumentDto
import com.cargo.customer.shared.data.remote.dto.UploadUrlDto
import com.cargo.customer.shared.domain.result.ApiResult

interface AuthRemoteDataSource {
    suspend fun getUploadUrl(documentType: Int, contentType: String): ApiResult<UploadUrlDto>
    suspend fun uploadImageToUrl(
        uploadUrl: String, bytes: ByteArray
    ): ApiResult<Unit>

    suspend fun submitDocumentData(
        documentType: Int,
        fileName: String,
        key: String,
        contentType: String,
        fileSizeInByte: String
    ): ApiResult<Unit>

    suspend fun getDocuments(): ApiResult<List<DocumentDto>>
}