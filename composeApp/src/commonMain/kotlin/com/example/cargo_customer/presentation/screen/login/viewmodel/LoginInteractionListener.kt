package com.example.cargo_customer.presentation.screen.login.viewmodel
sealed interface LoginInteractionListener {
    data class OnEmailChanged(val email: String) : LoginInteractionListener
    data class OnPasswordChanged(val password: String) : LoginInteractionListener
    data object OnPasswordVisibilityToggled : LoginInteractionListener
    data object OnLoginClicked : LoginInteractionListener
    data object OnGoogleClicked : LoginInteractionListener
    data object OnForgotPasswordClicked : LoginInteractionListener
    data object OnRegisterClicked : LoginInteractionListener
}