package com.example.cargo_customer.presentation.screen.login.viewmodel
sealed interface LoginEffect {
    data object NavigateToHome : LoginEffect
    data object NavigateToRegister : LoginEffect
    data object NavigateToForgetPassword : LoginEffect
    data object NavigateToVerifyEmail : LoginEffect
    data class ShowError(val message: String) : LoginEffect
}