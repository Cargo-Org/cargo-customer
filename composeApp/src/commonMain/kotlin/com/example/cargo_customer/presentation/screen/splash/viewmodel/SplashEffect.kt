package com.example.cargo_customer.presentation.screen.splash.viewmodel

sealed interface SplashEffect  {
    object NavigateToOnboarding : SplashEffect
    object NavigateToLogin : SplashEffect
    object NavigateToHome : SplashEffect
}