package com.example.cargo_customer.presentation.navigation.callbacks

data class SplashNavigationCallbacks(
    val onNavigateToOnboarding: () -> Unit,
    val onNavigateToLogin: () -> Unit,
    val onNavigateToHome: () -> Unit
)