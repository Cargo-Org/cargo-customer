package com.example.cargo_customer.presentation.di

import com.example.cargo_customer.presentation.screen.onboarding.viewmodel.OnboardingViewModel
import com.example.cargo_customer.presentation.screen.splash.viewmodel.SplashViewModel
import com.example.cargo_customer.presentation.screen.upload_docs.viewmodel.UploadDocumentViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { OnboardingViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { UploadDocumentViewModel(get(), get(), get())  }
}