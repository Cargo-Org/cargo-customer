package com.example.cargo_customer.presentation.navigation.callbacks

data class RegisterNavigationCallbacks(
    val onNavigateToLogin: () -> Unit,
    val onNavigateToVerifyEmail: () -> Unit,
)