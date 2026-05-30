package com.example.cargo_customer.presentation.screen.login.viewmodel
sealed interface LoginInteraction {
    data class OnEmailChanged(val email: String) : LoginInteraction
    data class OnPasswordChanged(val password: String) : LoginInteraction
    data object OnPasswordVisibilityToggled : LoginInteraction
    data object OnLoginClicked : LoginInteraction
    data object OnGoogleClicked : LoginInteraction
    data object OnForgotPasswordClicked : LoginInteraction
    data object OnRegisterClicked : LoginInteraction
}