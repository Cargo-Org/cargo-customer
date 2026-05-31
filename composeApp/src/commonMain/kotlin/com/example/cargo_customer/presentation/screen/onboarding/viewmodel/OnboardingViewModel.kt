package com.example.cargo_customer.presentation.screen.onboarding.viewmodel

import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.*
import com.cargo.customer.shared.domain.usecase.SetOnboardingFirstTimeUseCase
import com.example.cargo_customer.presentation.base.BaseViewModel

class OnboardingViewModel(
    private val setOnboardingFirstTimeUseCase: SetOnboardingFirstTimeUseCase,
) : BaseViewModel<OnboardingState, OnboardingEffect>(OnboardingState()),OnboardingInteractionListener{

    init {
        updateState { copy(data = getPages()) }
    }
    override fun onGetStartedClick() = completeOnboarding()

    private fun completeOnboarding(){
        tryToExecute(
            onSuccess = { sendEffect(OnboardingEffect.NavigateToLogin) },
            block = { setOnboardingFirstTimeUseCase() }
        )
    }
    private fun getPages(): List<OnBoardingPage> {
        return listOf(
            OnBoardingPage(
                imageRes = Res.drawable.onboarding1,
                title = Res.string.onboardingOneTitle,
                subtitle = Res.string.onboardingOneSubtitle
            ),
            OnBoardingPage(
                imageRes = Res.drawable.onboarding2,
                title = Res.string.onboardingTwoTitle,
                subtitle =Res.string.onboardingTwoSubtitle
            ),
            OnBoardingPage(
                imageRes = Res.drawable.onboarding3,
                title = Res.string.onboardingThreeTitle,
                subtitle = Res.string.onboardingThreeSubtitle
            )
        )
    }
}