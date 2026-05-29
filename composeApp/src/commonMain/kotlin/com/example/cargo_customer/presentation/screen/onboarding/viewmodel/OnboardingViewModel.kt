package com.example.cargo_customer.presentation.screen.onboarding.viewmodel

import com.cargo.customer.shared.domain.usecase.SetOnboardingFirstTimeUseCase
import com.example.cargo_customer.presentation.base.BaseViewModel

class OnboardingViewModel(
    private val setOnboardingFirstTimeUseCase: SetOnboardingFirstTimeUseCase,
) : BaseViewModel<Unit, OnboardingEffect>(),OnboardingInteractionListener{

    override fun onGetStartedClick() = completeOnboarding()

    private fun completeOnboarding(){
        tryToExecute(
            onSuccess = { sendEffect(OnboardingEffect.NavigateToLogin) },
            block = { setOnboardingFirstTimeUseCase() }
        )
    }
}