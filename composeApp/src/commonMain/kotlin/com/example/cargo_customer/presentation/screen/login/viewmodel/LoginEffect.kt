package com.example.cargo_customer.presentation.screen.login.viewmodel
sealed interface LoginEffect {
    data class NavigateToHome(val email: String) : LoginEffect
    data object NavigateToRegister : LoginEffect
    data object NavigateToForgetPassword : LoginEffect
    data class ShowError(val message: String) : LoginEffect
}