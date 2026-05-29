package com.example.cargo_customer.presentation.screen.splash.viewmodel

import com.cargo.customer.shared.domain.usecase.IsOnboardingFirstTimeUseCase
import com.example.cargo_customer.presentation.base.BaseViewModel

class SplashViewModel (
    private val isOnboardingFirstTimeUseCase: IsOnboardingFirstTimeUseCase,
): BaseViewModel<Unit, SplashEffect>() {

    fun determineNextDestination(){
        tryToExecute(
            onSuccess = {
                if(it) sendEffect(SplashEffect.NavigateToOnboarding)
                else sendEffect(SplashEffect.NavigateToLogin) //TODO will change when implement login logic and check if go to login or home
            },
            block = { isOnboardingFirstTimeUseCase() }
        )
    }
}