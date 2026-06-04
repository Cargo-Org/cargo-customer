package com.cargo.customer.shared.data.remote.datasource

import com.cargo.customer.shared.data.local.datastore.TokenStorage
import com.cargo.customer.shared.data.remote.client.NetworkClient
import com.cargo.customer.shared.data.remote.dto.DocumentDto
import com.cargo.customer.shared.data.remote.dto.SubmitDocumentRequest
import com.cargo.customer.shared.data.remote.dto.UploadUrlDto
import com.cargo.customer.shared.data.remote.util.ApiConstants.DOCUMENTS_ENDPOINT
import com.cargo.customer.shared.data.remote.util.ApiConstants.UPLOAD_URL_ENDPOINT
import com.cargo.customer.shared.data.remote.util.safeApiCall
import com.cargo.customer.shared.domain.exception.GetDocumentsException
import com.cargo.customer.shared.domain.exception.UploadUrlException
import com.cargo.customer.shared.domain.result.ApiResult
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRemoteDataSourceImpl(
    private val client: NetworkClient,
    private val tokenStorage: TokenStorage
) : AuthRemoteDataSource {

    override suspend fun getUploadUrl(): ApiResult<UploadUrlDto> {
        return when (val result = safeApiCall<UploadUrlDto> {
            client.post(UPLOAD_URL_ENDPOINT)
        }) {
            is ApiResult.Success -> result
            is ApiResult.Error -> {
                ApiResult.Error(UploadUrlException())
            }
        }
    }

    override suspend fun uploadImageToUrl(uploadUrl: String, bytes: ByteArray): ApiResult<Unit> {
        return safeApiCall<Unit> {
            client.post(uploadUrl) {
                setBody(bytes)
                contentType(ContentType.Application.OctetStream)
            }
        }
    }

    override suspend fun submitDocumentData(
        documentType: Int,
        fileName: String,
        key: String,
        contentType: String,
        fileSizeInByte: String
    ): ApiResult<Unit> {
        return safeApiCall<Unit> {
            client.post(DOCUMENTS_ENDPOINT) {
                setBody(
                    SubmitDocumentRequest(
                        documentType = documentType,
                        objectKey = key,
                        originalFileName = fileName,
                        contentType = contentType,
                        fileSizeBytes = fileSizeInByte
                    )
                )
            }
        }
    }

    override suspend fun getDocuments(): ApiResult<List<DocumentDto>> {
        return when (val result = safeApiCall<List<DocumentDto>> {
            client.get(DOCUMENTS_ENDPOINT)
        }) {
            is ApiResult.Success -> result
            is ApiResult.Error -> {
                ApiResult.Error(GetDocumentsException())
            }
        }
    }
}