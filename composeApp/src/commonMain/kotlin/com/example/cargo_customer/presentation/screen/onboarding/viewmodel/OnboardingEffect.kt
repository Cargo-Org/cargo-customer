package com.example.cargo_customer.presentation.screen.onboarding.viewmodel

sealed interface OnboardingEffect {
    object NavigateToLogin : OnboardingEffect
}