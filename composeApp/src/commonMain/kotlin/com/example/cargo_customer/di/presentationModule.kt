package com.example.cargo_customer.di

import com.example.cargo_customer.presentation.screen.register.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { RegisterViewModel(get()) }
}