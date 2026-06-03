package com.cargo.customer.shared.data.remote.util

import com.cargo.customer.shared.data.remote.dto.ApiErrorResponse
import com.cargo.customer.shared.data.remote.dto.ValidationErrorResponse
import com.cargo.customer.shared.domain.exception.CargoException
import com.cargo.customer.shared.domain.exception.ConflictException
import com.cargo.customer.shared.domain.exception.NoInternetException
import com.cargo.customer.shared.domain.exception.NotFoundException
import com.cargo.customer.shared.domain.exception.ServerException
import com.cargo.customer.shared.domain.exception.UnauthorizedException
import com.cargo.customer.shared.domain.exception.UnknownException
import com.cargo.customer.shared.domain.result.ApiResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.io.IOException

suspend inline fun <reified T> safeApiCall(
    execute: suspend () -> HttpResponse
): ApiResult<T> {

    return try {

        val response = execute()

        when (response.status.value) {

            in 200..299 -> {
                ApiResult.Success(
                    response.body<T>()
                )
            }

            in 400..499 -> {
                val errorBody = try {
                    response.body<ApiErrorResponse>()
                } catch (e: Exception) {
                    response.body<ValidationErrorResponse>()
                }
                ApiResult.Error(
                    ConflictException(errorResponse = errorBody)
                )
            }

            in 500..599 -> {
                ApiResult.Error(ServerException())
            }

            else -> {
                ApiResult.Error(UnknownException())
            }
        }

    } catch (e: IOException) {

        ApiResult.Error(NoInternetException())

    } catch (e: UnresolvedAddressException) {

        ApiResult.Error(NoInternetException())

    } catch (e: CargoException) {

        ApiResult.Error(e)

    } catch (e: Exception) {

        ApiResult.Error(UnknownException())
    }
}