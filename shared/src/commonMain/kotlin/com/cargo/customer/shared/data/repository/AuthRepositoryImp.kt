package com.cargo.customer.shared.data.repository

import com.cargo.customer.shared.data.local.datasource.AuthLocalDataSource
import com.cargo.customer.shared.data.mapper.toDomain
import com.cargo.customer.shared.data.remote.datasource.AuthRemoteDataSource
import com.cargo.customer.shared.domain.model.DocumentModel
import com.cargo.customer.shared.domain.model.UploadUrlModel
import com.cargo.customer.shared.domain.repository.AuthRepository
import com.cargo.customer.shared.domain.result.ApiResult

class AuthRepositoryImp(
    private val remote: AuthRemoteDataSource,
    private val local: AuthLocalDataSource
) : AuthRepository {

    override suspend fun getUploadUrl(
        documentType: Int,
        contentType: String
    ): ApiResult<UploadUrlModel> {
        return when (val result =
            remote.getUploadUrl(documentType = documentType, contentType = contentType)) {
            is ApiResult.Success -> {
                ApiResult.Success(result.data.toDomain())
            }

            is ApiResult.Error -> result
        }
    }

    override suspend fun uploadImageToUrl(uploadUrl: String, bytes: ByteArray): ApiResult<Unit> {
        return when (val result = remote.uploadImageToUrl(uploadUrl, bytes)) {
            is ApiResult.Success -> {
                ApiResult.Success(Unit)
            }

            is ApiResult.Error -> result
        }
    }

    override suspend fun submitDocumentData(
        documentType: Int,
        fileName: String,
        key: String,
        contentType: String,
        fileSizeInByte: String
    ): ApiResult<Unit> {
        return when (val result =
            remote.submitDocumentData(documentType, fileName, key, contentType, fileSizeInByte)) {
            is ApiResult.Success -> {
                ApiResult.Success(Unit)
            }

            is ApiResult.Error -> result
        }
    }

    override suspend fun getDocuments(): ApiResult<List<DocumentModel>> {
        return when (val result = remote.getDocuments()) {
            is ApiResult.Success -> {
                ApiResult.Success(result.data.map { it.toDomain() })
            }

            is ApiResult.Error -> result
        }
    }
}