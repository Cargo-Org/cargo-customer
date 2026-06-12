package com.example.cargo_customer.presentation.screen.register.viewmodel

sealed interface RegisterUiEffect {
    object NavigateToVerifyEmail : RegisterUiEffect
    object NavigateToLogin : RegisterUiEffect
    data class ShowToast(val message: String) : RegisterUiEffect
}