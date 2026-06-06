package com.cargo.customer.shared.domain.repository

import com.cargo.customer.shared.domain.model.DocumentModel
import com.cargo.customer.shared.domain.model.UploadUrlModel
import com.cargo.customer.shared.domain.result.ApiResult

interface AuthRepository {
    suspend fun getUploadUrl(documentType:Int,contentType:String): ApiResult<UploadUrlModel>
    suspend fun uploadImageToUrl(
        uploadUrl: String,
        bytes: ByteArray
    ): ApiResult<Unit>
    suspend fun submitDocumentData(
        documentType: Int,
        fileName: String,
        key : String,
        contentType: String,
        fileSizeInByte: String
    ): ApiResult<Unit>
    suspend fun getDocuments(): ApiResult<List<DocumentModel>>
}