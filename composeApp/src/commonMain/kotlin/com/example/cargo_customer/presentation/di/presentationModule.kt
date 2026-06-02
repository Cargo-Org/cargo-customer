package com.example.cargo_customer.presentation.di

import com.example.cargo_customer.presentation.screen.login.viewmodel.LoginViewModel
import com.example.cargo_customer.presentation.screen.onboarding.viewmodel.OnboardingViewModel
import com.example.cargo_customer.presentation.screen.register.RegisterViewModel
import com.example.cargo_customer.presentation.screen.splash.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { OnboardingViewModel(get()) }
    viewModel { SplashViewModel(get() ,get ()) }
    viewModel { RegisterViewModel(get()) }
}