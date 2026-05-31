package com.example.cargo_customer.presentation.screen.splash.viewmodel

import com.cargo.customer.shared.domain.usecase.IsOnboardingFirstTimeUseCase
import com.example.cargo_customer.presentation.base.BaseViewModel

class SplashViewModel (
    private val isOnboardingFirstTimeUseCase: IsOnboardingFirstTimeUseCase,
): BaseViewModel<SplashState, SplashEffect>(SplashState()) {

    fun determineNextDestination() {
        updateState { copy(isLoading = true) }

        tryToExecute(
            onSuccess = {
                updateState { copy(isLoading = false) }
                if (it) {
                    sendEffect(SplashEffect.NavigateToOnboarding)
                } else {
                    sendEffect(SplashEffect.NavigateToLogin)
                }
            },
            onError = {
                updateState { copy(isLoading = false) }
            },
            block = { isOnboardingFirstTimeUseCase() }
        )
    }
}