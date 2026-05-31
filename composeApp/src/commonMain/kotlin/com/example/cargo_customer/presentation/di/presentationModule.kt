package com.example.cargo_customer.presentation.di

import com.example.cargo_customer.presentation.screen.onboarding.viewmodel.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { OnboardingViewModel(get()) }
}