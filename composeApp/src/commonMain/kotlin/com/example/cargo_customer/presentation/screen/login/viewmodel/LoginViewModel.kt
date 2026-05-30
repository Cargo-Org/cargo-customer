package com.example.cargo_customer.presentation.screen.login.viewmodel

import com.cargo.customer.shared.domain.usecase.LoginUseCase
import com.example.cargo_customer.presentation.base.BaseViewModel

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginUiState, LoginEffect>(LoginUiState()) {

    fun onAction(action: LoginInteraction) {
        when (action) {
            is LoginInteraction.OnEmailChanged -> updateState { copy(email = action.email) }
            is LoginInteraction.OnPasswordChanged -> updateState { copy(password = action.password) }
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

    }
}