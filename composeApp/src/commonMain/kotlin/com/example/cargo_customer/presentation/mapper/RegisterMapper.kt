package com.example.cargo_customer.presentation.mapper

import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.already_registered
import cargo_customer.composeapp.generated.resources.internal_server_error
import cargo_customer.composeapp.generated.resources.no_internet_connection
import cargo_customer.composeapp.generated.resources.please_resolve_the_following_issues
import cargo_customer.composeapp.generated.resources.service_not_found
import cargo_customer.composeapp.generated.resources.unexpected_error
import com.cargo.customer.shared.data.remote.dto.ApiErrorResponse
import com.cargo.customer.shared.data.remote.dto.RegisterRequest
import com.cargo.customer.shared.data.remote.dto.ValidationErrorResponse
import com.cargo.customer.shared.domain.exception.ConflictException
import com.cargo.customer.shared.domain.exception.NoInternetException
import com.cargo.customer.shared.domain.exception.NotFoundException
import com.cargo.customer.shared.domain.exception.ServerException
import com.cargo.customer.shared.domain.exception.UnauthorizedException
import com.cargo.customer.shared.domain.usecase.auth.RegisterValidationUseCases
import com.example.cargo_customer.presentation.core.ui.UiText
import com.example.cargo_customer.presentation.core.ui.toUiTextOrNull
import com.example.cargo_customer.presentation.screen.register.viewmodel.RegisterUiState

fun RegisterUiState.validate(validation: RegisterValidationUseCases): RegisterUiState {
    val nameError = validation.validateName(name).toUiTextOrNull()
    val emailError = validation.validateEmail(email).toUiTextOrNull()
    val phoneError = validation.validatePhone(phone).toUiTextOrNull()
    val passwordError = validation.validatePassword(password).toUiTextOrNull()
    return copy(
        nameError = nameError,
        emailError = emailError,
        phoneError = phoneError,
        passwordError = passwordError
    )
}

fun RegisterUiState.hasErrors(): Boolean {
    return nameError != null || emailError != null || phoneError != null || passwordError != null
}

fun RegisterUiState.toRegisterRequest(): RegisterRequest {
    val nameParts = name.trim().split(" ")
    val firstName = nameParts.firstOrNull().orEmpty()
    val lastName = nameParts.drop(1).joinToString(" ")
    return RegisterRequest(
        email = email.trim(),
        password = password,
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phone.trim()
    )
}

fun Throwable.toRegisterUiError(): UiText {
    return when (this) {
        is NoInternetException -> UiText.Resource(Res.string.no_internet_connection)
        is UnauthorizedException -> UiText.Resource(Res.string.already_registered)
        is NotFoundException -> UiText.Resource(Res.string.service_not_found)
        is ServerException -> UiText.Resource(Res.string.internal_server_error)
        is ConflictException -> handleConflictException(this)
        else -> UiText.Resource(Res.string.unexpected_error)
    }
}

private fun handleConflictException(exception: ConflictException): UiText {
    return when (val response = exception.errorResponse) {
        is ApiErrorResponse -> {
            UiText.Dynamic(response.detail)
        }

        is ValidationErrorResponse -> {
            val errors = mapApiErrors(response.errors.values.flatten())
            val joinedErrors = UiText.Joined(
                texts = errors,
                separator = "\n- ",
                prefix = "\n- "
            )
            UiText.ResourceWithArgs(
                Res.string.please_resolve_the_following_issues,
                listOf(joinedErrors)
            )
        }
    }
}
