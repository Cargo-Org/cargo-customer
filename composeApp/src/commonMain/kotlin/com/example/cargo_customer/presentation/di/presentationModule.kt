package com.example.cargo_customer.presentation.di

import com.example.cargo_customer.presentation.screen.login.viewmodel.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LoginViewModel(get()) }
}