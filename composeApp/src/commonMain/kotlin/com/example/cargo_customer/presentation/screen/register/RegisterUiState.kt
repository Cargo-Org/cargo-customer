package com.example.cargo_customer.presentation.screen.register

import com.example.cargo_customer.presentation.core.ui.UiText

data class RegisterUiState(
    val name: String = "",
    val nameError: UiText? = null,
    val email: String = "",
    val emailError: UiText? = null,
    val phone: String = "",
    val phoneError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val error: UiText? = null
)