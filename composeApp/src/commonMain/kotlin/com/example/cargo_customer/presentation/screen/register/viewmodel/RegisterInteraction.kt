package com.example.cargo_customer.presentation.screen.register.viewmodel

sealed interface RegisterInteraction {

    data class OnNameChanged(val value: String) : RegisterInteraction
    data class OnEmailChanged(val value: String) : RegisterInteraction
    data class OnPhoneChanged(val value: String) : RegisterInteraction
    data class OnPasswordChanged(val value: String) : RegisterInteraction

    data object OnTogglePasswordVisibility : RegisterInteraction
    data object OnRegisterClicked : RegisterInteraction
    data object OnSignInClicked : RegisterInteraction
}