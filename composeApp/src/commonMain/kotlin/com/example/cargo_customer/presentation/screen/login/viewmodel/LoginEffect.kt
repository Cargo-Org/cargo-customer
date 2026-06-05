package com.example.cargo_customer.presentation.screen.login.viewmodel

import com.example.cargo_customer.presentation.core.ui.UiText

sealed interface LoginEffect {
    object NavigateToHome : LoginEffect
    object NavigateToRegister : LoginEffect
    object NavigateToForgetPassword : LoginEffect
    object NavigateToVerifyEmail : LoginEffect
    data class ShowError(val message: UiText) : LoginEffect
}