package com.example.cargo_customer.presentation.screen.login.viewmodel

import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.already_registered
import cargo_customer.composeapp.generated.resources.email_not_verify
import cargo_customer.composeapp.generated.resources.internal_server_error
import cargo_customer.composeapp.generated.resources.invalid_credentials
import cargo_customer.composeapp.generated.resources.invalid_email
import cargo_customer.composeapp.generated.resources.no_internet_connection
import cargo_customer.composeapp.generated.resources.service_not_found
import cargo_customer.composeapp.generated.resources.unexpected_error
import cargo_customer.composeapp.generated.resources.weak_password
import com.cargo.customer.shared.data.remote.dto.ApiErrorResponse
import com.cargo.customer.shared.domain.exception.ConflictException
import com.cargo.customer.shared.domain.exception.NoInternetException
import com.cargo.customer.shared.domain.exception.NotFoundException
import com.cargo.customer.shared.domain.exception.ServerException
import com.cargo.customer.shared.domain.exception.UnauthorizedException
import com.cargo.customer.shared.domain.result.ApiResult
import com.cargo.customer.shared.domain.usecase.LoginUseCase
import com.example.cargo_customer.presentation.base.BaseViewModel
import com.example.cargo_customer.presentation.core.ui.UiText

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginUiState, LoginEffect>(LoginUiState()) {

    fun onAction(action: LoginInteraction) {
        when (action) {
            is LoginInteraction.OnEmailChanged -> updateState { copy(email = action.email , emailError = null , passwordError = null ,errorMessage = null) }
            is LoginInteraction.OnPasswordChanged -> updateState { copy(password = action.password , passwordError = null ,emailError = null, errorMessage = null) }
            is LoginInteraction.OnPasswordVisibilityToggled -> updateState { copy(isPasswordVisible = !isPasswordVisible) }
            is LoginInteraction.OnLoginClicked -> loginEmailAndPassword()
            is LoginInteraction.OnGoogleClicked ->loginWithGoogle()
            is LoginInteraction.OnForgotPasswordClicked -> sendEffect(LoginEffect.NavigateToForgetPassword)
            is LoginInteraction.OnRegisterClicked -> sendEffect(LoginEffect.NavigateToRegister)
        }
    }
    private fun loginWithGoogle(){

    }
    private fun loginEmailAndPassword() {
        val email = state.value.email.trim()
        val password = state.value.password
        var hasError = false
        if (!loginUseCase.isEmailValid(email)) {
            updateState { copy(emailError = UiText.Resource(Res.string.invalid_email)) }
            hasError = true
        }
        if (!loginUseCase.isPasswordValid(password)) {
            updateState { copy(passwordError = UiText.Resource(Res.string.weak_password)) }
            hasError = true
        }
        if (hasError) return
        tryToExecute(
            block = {loginUseCase(email, password)},
            onStart = {updateState { copy(isLoading = true , errorMessage = null)}},
            onSuccess = { result ->
                when(result){
                    is ApiResult.Success -> {
                        // send effect navigate to home screen
                    }
                    is ApiResult.Error -> {
                        val errorMessage = when (val exception = result.exception){
                            is NoInternetException -> UiText.Resource(Res.string.no_internet_connection)
                            is UnauthorizedException -> UiText.Resource(Res.string.already_registered)
                            is NotFoundException -> UiText.Resource(Res.string.service_not_found)
                            is ServerException -> UiText.Resource(Res.string.internal_server_error)
                            is ConflictException ->
                                when (val response = exception.errorResponse) {
                                    is ApiErrorResponse -> {
                                        if(response.title == "Login.EmailNotVerified"){
                                            sendEffect(LoginEffect.NavigateToVerifyEmail)
                                            UiText.Resource(Res.string.email_not_verify)
                                        }
                                        else{
                                            UiText.Resource(Res.string.invalid_credentials)
                                        }
                                    }
                                    else -> UiText.Resource(Res.string.unexpected_error)
                                }
                            else -> UiText.Resource(Res.string.unexpected_error)
                        }
                        updateState { copy(errorMessage = errorMessage ) }
                    }
                }
            },
            onError = { updateState { copy(errorMessage = UiText.Resource(Res.string.invalid_email))}},
            onEnd = {updateState { copy(isLoading = false)}}
        )
    }
}