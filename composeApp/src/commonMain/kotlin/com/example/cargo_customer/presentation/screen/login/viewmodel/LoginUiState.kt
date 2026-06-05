package com.example.cargo_customer.presentation.screen.login.viewmodel

import com.example.cargo_customer.presentation.core.ui.ui.UiText

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
    val errorMessage: UiText? = null,
)