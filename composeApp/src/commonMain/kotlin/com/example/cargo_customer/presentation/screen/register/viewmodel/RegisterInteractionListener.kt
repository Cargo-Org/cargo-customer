package com.example.cargo_customer.presentation.screen.register.viewmodel

sealed interface RegisterInteractionListener {

    data class OnNameChanged(val value: String) : RegisterInteractionListener
    data class OnEmailChanged(val value: String) : RegisterInteractionListener
    data class OnPhoneChanged(val value: String) : RegisterInteractionListener
    data class OnPasswordChanged(val value: String) : RegisterInteractionListener

    data object OnTogglePasswordVisibility : RegisterInteractionListener
    data object OnRegisterClicked : RegisterInteractionListener
    data object OnSignInClicked : RegisterInteractionListener
}