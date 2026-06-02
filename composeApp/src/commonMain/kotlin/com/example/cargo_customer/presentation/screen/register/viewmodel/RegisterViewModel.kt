package com.example.cargo_customer.presentation.screen.register.viewmodel

import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.already_registered
import cargo_customer.composeapp.generated.resources.internal_server_error
import cargo_customer.composeapp.generated.resources.no_internet_connection
import cargo_customer.composeapp.generated.resources.service_not_found
import cargo_customer.composeapp.generated.resources.unexpected_error
import com.cargo.customer.shared.data.remote.dto.ApiErrorResponse
import com.cargo.customer.shared.data.remote.dto.RegisterRequest
import com.cargo.customer.shared.data.remote.dto.ValidationErrorResponse
import com.cargo.customer.shared.domain.exception.ConflictException
import com.cargo.customer.shared.domain.result.ApiResult
import com.cargo.customer.shared.domain.exception.NoInternetException
import com.cargo.customer.shared.domain.exception.UnauthorizedException
import com.cargo.customer.shared.domain.exception.NotFoundException
import com.cargo.customer.shared.domain.exception.ServerException
import com.cargo.customer.shared.domain.usecase.auth.RegisterUseCase
import com.cargo.customer.shared.domain.validation.Validator
import com.example.cargo_customer.presentation.base.BaseViewModel
import com.example.cargo_customer.presentation.core.ui.UiText
import com.example.cargo_customer.presentation.core.ui.toUiTextOrNull


class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<RegisterUiState, RegisterUiEffect>(
    initialValue = RegisterUiState()
) {

    fun onInteraction(interaction: RegisterInteraction) {
        when (interaction) {

            is RegisterInteraction.OnNameChanged ->
                updateState { copy(name = interaction.value, nameError = null) }

            is RegisterInteraction.OnEmailChanged ->
                updateState { copy(email = interaction.value, emailError = null ) }

            is RegisterInteraction.OnPhoneChanged ->
                updateState { copy(phone = interaction.value, phoneError = null) }

            is RegisterInteraction.OnPasswordChanged ->
                updateState { copy(password = interaction.value, passwordError = null) }

            is RegisterInteraction.OnTogglePasswordVisibility ->
                updateState { copy(isPasswordVisible = !isPasswordVisible) }

            is RegisterInteraction.OnRegisterClicked ->
                onRegisterClicked()

            is RegisterInteraction.OnSignInClicked ->
                sendEffect(RegisterUiEffect.NavigateToLogin)
        }
    }

    fun onRegisterClicked() {
        val currentState = state.value

        val nameValidation =
            Validator.validateName(currentState.name)

        val emailValidation =
            Validator.validateEmail(currentState.email)

        val phoneValidation =
            Validator.validatePhone(currentState.phone)

        val passwordValidation =
            Validator.validatePassword(currentState.password)

        val nameError = nameValidation.toUiTextOrNull()
        val emailError = emailValidation.toUiTextOrNull()
        val phoneError = phoneValidation.toUiTextOrNull()
        val passwordError = passwordValidation.toUiTextOrNull()

        val hasValidationError = listOf(
            nameError,
            emailError,
            phoneError,
            passwordError
        ).any { it != null }

        if (hasValidationError) {

            updateState {
                copy(
                    nameError = nameError,
                    emailError = emailError,
                    phoneError = phoneError,
                    passwordError = passwordError
                )
            }
            return
        }


        val nameParts = currentState.name.trim().split(" ")
        val firstName = nameParts.firstOrNull().orEmpty()
        val lastName = nameParts.drop(1).joinToString(" ")
        val request = RegisterRequest(
            email = currentState.email,
            password = currentState.password,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = currentState.phone
        )

        tryToExecute(
            block = { registerUseCase(request) },
            onStart = {
                updateState { copy(isLoading = true, error = null) }
            },
            onSuccess = { result ->
                when (result) {
                    is ApiResult.Success -> {
                        sendEffect(RegisterUiEffect.NavigateToVerifyEmail)
                    }

                    is ApiResult.Error -> {
                        val errorMessage = when (val exception = result.exception) {
                            is NoInternetException -> UiText.Resource(Res.string.no_internet_connection)
                            is UnauthorizedException -> UiText.Resource(Res.string.already_registered)
                            is NotFoundException -> UiText.Resource(Res.string.service_not_found)
                            is ServerException -> UiText.Resource(Res.string.internal_server_error)
                            is ConflictException ->
                                when (val response = exception.errorResponse) {
                                    is ApiErrorResponse -> {
                                        UiText.Dynamic(response.detail)
                                    }

                                    is ValidationErrorResponse -> {
                                        UiText.Dynamic(
                                            buildString {
                                                appendLine("Please resolve the following issues:")
                                                response.errors.values.flatten()
                                                    .forEachIndexed { i, error ->
                                                        appendLine("${i + 1}- $error")
                                                    }
                                            }
                                        )
                                    }
                                }

                            else ->
                                UiText.Resource(Res.string.unexpected_error)
                        }
                        updateState { copy(error = errorMessage) }
                    }
                }
            },
            onError = {
                updateState { copy(error = UiText.Resource(Res.string.unexpected_error)) }
            },
            onEnd = {
                updateState { copy(isLoading = false) }
            }
        )
    }
}