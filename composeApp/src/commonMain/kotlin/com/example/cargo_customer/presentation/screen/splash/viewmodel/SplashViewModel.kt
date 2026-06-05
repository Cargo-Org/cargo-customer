package com.example.cargo_customer.presentation.screen.splash.viewmodel

import com.cargo.customer.shared.domain.usecase.IsLoggedInUseCase
import com.cargo.customer.shared.domain.usecase.IsOnboardingFirstTimeUseCase
import com.example.cargo_customer.presentation.base.BaseViewModel

class SplashViewModel (
    private val isOnboardingFirstTimeUseCase: IsOnboardingFirstTimeUseCase,
    private val isLoggedInUseCase: IsLoggedInUseCase
): BaseViewModel<SplashState, SplashEffect>(SplashState()) {

    fun determineNextDestination() {
        updateState { copy(isLoading = true) }

        tryToExecute(
            onSuccess = { (isFirstTime, isLoggedIn) ->
                updateState { copy(isLoading = false) }
                when {
                    isFirstTime -> sendEffect(SplashEffect.NavigateToOnboarding)
                    isLoggedIn -> sendEffect(SplashEffect.NavigateToHome)
                    else -> sendEffect(SplashEffect.NavigateToLogin)
                }
            },
            onError = {
                updateState { copy(isLoading = false) }
                sendEffect(SplashEffect.NavigateToLogin)
            },
            block = {
                val isFirstTime = isOnboardingFirstTimeUseCase()
                val isLoggedIn = isLoggedInUseCase()
                Pair(isFirstTime, isLoggedIn)
            },
        )
    }
}