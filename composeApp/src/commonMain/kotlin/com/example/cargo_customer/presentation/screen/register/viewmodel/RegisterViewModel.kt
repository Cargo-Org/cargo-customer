package com.example.cargo_customer.presentation.screen.register.viewmodel

import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.unexpected_error
import com.cargo.customer.shared.domain.result.ApiResult
import com.cargo.customer.shared.domain.usecase.auth.RegisterUseCase
import com.cargo.customer.shared.domain.usecase.auth.RegisterValidationUseCases
import com.example.cargo_customer.presentation.base.BaseViewModel
import com.example.cargo_customer.presentation.core.ui.UiText
import com.example.cargo_customer.presentation.mapper.hasErrors
import com.example.cargo_customer.presentation.mapper.toRegisterRequest
import com.example.cargo_customer.presentation.mapper.toRegisterUiError
import com.example.cargo_customer.presentation.mapper.validate

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val validation: RegisterValidationUseCases
) : BaseViewModel<RegisterUiState, RegisterUiEffect>(
    initialValue = RegisterUiState()
) {

    fun onInteraction(interaction: RegisterInteractionListener) {
        when (interaction) {
            is RegisterInteractionListener.OnNameChanged ->
                updateState { copy(name = interaction.value, nameError = null, error = null) }

            is RegisterInteractionListener.OnEmailChanged ->
                updateState { copy(email = interaction.value, emailError = null, error = null) }

            is RegisterInteractionListener.OnPhoneChanged ->
                updateState { copy(phone = interaction.value, phoneError = null, error = null) }

            is RegisterInteractionListener.OnPasswordChanged ->
                updateState { copy(password = interaction.value, passwordError = null, error = null) }

            is RegisterInteractionListener.OnTogglePasswordVisibility ->
                updateState { copy(isPasswordVisible = !isPasswordVisible) }

            is RegisterInteractionListener.OnRegisterClicked ->
                onRegisterClicked()

            is RegisterInteractionListener.OnSignInClicked ->
                sendEffect(RegisterUiEffect.NavigateToLogin)
        }
    }

    private fun onRegisterClicked() {
        val currentState = state.value
        val validatedState = currentState.validate(validation)

        if (validatedState.hasErrors()) {
            updateState { validatedState }
            return
        }

        val request = currentState.toRegisterRequest()

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
                        val errorText = result.exception.toRegisterUiError()
                        updateState { copy(error = errorText) }
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