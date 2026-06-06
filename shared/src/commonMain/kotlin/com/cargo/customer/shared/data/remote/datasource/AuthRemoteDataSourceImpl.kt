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
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRemoteDataSourceImpl(
    private val client: NetworkClient,
    private val tokenStorage: TokenStorage
) : AuthRemoteDataSource {

    override suspend fun getUploadUrl(documentType:Int,contentType:String): ApiResult<UploadUrlDto> {
        println("Content Type is : $contentType")
        return safeApiCall<UploadUrlDto> {
            client.get(UPLOAD_URL_ENDPOINT) {
                contentType(ContentType.Application.Json)
                parameter(DOCUMENT_TYPE_KEY, documentType)
                parameter(CONTENT_TYPE_KEY, contentType)
            }

        }.let { result ->
            when (result) {
                is ApiResult.Success -> result
                is ApiResult.Error -> {
                    println(result.exception)
                    ApiResult.Error(UploadUrlException(""))
                }
            }
        }
    }

    override suspend fun uploadImageToUrl(uploadUrl: String, bytes: ByteArray): ApiResult<Unit> {
        return safeApiCall<Unit> {
            println("URL: $uploadUrl")
            client.put(uploadUrl) {
                setBody(bytes)
                contentType(ContentType.Application.OctetStream)
            }
        }
    }
//don't forget let user choose camera of select document
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

    private companion object{
        const val  DOCUMENT_TYPE_KEY = "documentType"
        const val CONTENT_TYPE_KEY = "contentType"
    }
}


/* {
  "customerId": "9b966610-40e4-41ef-a8ce-40f0123055a1",
  "fullName": "Hend Sayed",
  "accessToken": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJKal8zNGtvNG84OWQzY0N2dVg2cndWYy1Cb2J1Sk4yc2FzSUxZWHFQMzlzIn0.eyJleHAiOjE3ODA4NDgyNDYsImlhdCI6MTc4MDc2MTg0NiwianRpIjoib25ydHJvOjg4MzdjYTNkLTFiY2UtOWI1Zi04YzFmLTAyNTU5M2UxMmJmNSIsImlzcyI6Imh0dHBzOi8vY2FyZ28ubm9ydGhldXJvcGUuY2xvdWRhcHAuYXp1cmUuY29tL2tleWNsb2FrL3JlYWxtcy9jYXJnby1jdXN0b21lciIsImF1ZCI6WyJjYXJnby1iYWNrZW5kIiwiYWNjb3VudCJdLCJzdWIiOiI3YTU1NDRlYy01ODVkLTQ3ZDAtOGI1Ni1jMDRhMzJlMTRiNDgiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJjYXJnby1iYWNrZW5kIiwic2lkIjoiZ3RVclBUNW5VWDNkMU40Y1QxOFBkMkhwIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIvKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1jYXJnby1jdXN0b21lciIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJjdXN0b21lciJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6IkhlbmQgU2F5ZWQiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJoZW5kc2F5ZWQ4NTNAZ21haWwuY29tIiwiZ2l2ZW5fbmFtZSI6IkhlbmQiLCJmYW1pbHlfbmFtZSI6IlNheWVkIiwiZW1haWwiOiJoZW5kc2F5ZWQ4NTNAZ21haWwuY29tIn0.A-Mf-EsW6ie5tEto3flfBqUyx4st2Yj7c9WoOqAeysHygskgYRn98BDd128R33CeM2omKUYD7Z1VO5ytdRNS1x01amuTqnn2uhlZ_2Ztk0aA-uF7_gZjLS4PN7NgpMdKkkO3hujKcBteop0mlCkAWWqAhMAFxp0B7cyWVswot1VvkS1GI6rMC9OD-ScnKz8vEiesoLuIRAAg3H818M7vH9dA1lFzq3wtVGIBCR8JQVxEsT03PyUI-RhmEHiEHJ0lUfEgMjNhIBtOF0zXYls7x5KqtJYe6saE69fzxJMiec0qmkynQNrCQ_7xfGhIb9GBbGvUGqhRpE4ZmwWRWaGvjg",
  "refreshToken": "eyJhbGciOiJIUzUxMiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIxZjljYmFmZi0yMWU1LTQzMWItODFkYi1iZGMwOTU2OTMwMTkifQ.eyJleHAiOjE3ODMzNTM4NDYsImlhdCI6MTc4MDc2MTg0NiwianRpIjoiNjdmZWEwMDYtMTgwYS1jZjdlLWM5MjMtNjIyZTUyZTEwMjE4IiwiaXNzIjoiaHR0cHM6Ly9jYXJnby5ub3J0aGV1cm9wZS5jbG91ZGFwcC5henVyZS5jb20va2V5Y2xvYWsvcmVhbG1zL2NhcmdvLWN1c3RvbWVyIiwiYXVkIjoiaHR0cHM6Ly9jYXJnby5ub3J0aGV1cm9wZS5jbG91ZGFwcC5henVyZS5jb20va2V5Y2xvYWsvcmVhbG1zL2NhcmdvLWN1c3RvbWVyIiwic3ViIjoiN2E1NTQ0ZWMtNTg1ZC00N2QwLThiNTYtYzA0YTMyZTE0YjQ4IiwidHlwIjoiUmVmcmVzaCIsImF6cCI6ImNhcmdvLWJhY2tlbmQiLCJzaWQiOiJndFVyUFQ1blVYM2QxTjRjVDE4UGQySHAiLCJzY29wZSI6Im9wZW5pZCBiYXNpYyBzZXJ2aWNlX2FjY291bnQgcm9sZXMgZW1haWwgd2ViLW9yaWdpbnMgcHJvZmlsZSBhY3IiLCJhdWRfeCI6WyJjYXJnby1iYWNrZW5kIiwiYWNjb3VudCJdfQ.4Kks8Bsxs0wd66td9BHN7VogoUib2K_-wIGdC4govVHDCZ-wxvdlI8r4AqDtR-wkUW7Owc2D3zhgeVgzu6r9DQ",
  "accessTokenExpiresIn": 86400,
  "refreshTokenExpiresIn": 2592000
}
*
* */