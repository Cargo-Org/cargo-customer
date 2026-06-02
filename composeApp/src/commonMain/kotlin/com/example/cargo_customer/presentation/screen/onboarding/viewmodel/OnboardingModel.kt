package com.example.cargo_customer.presentation.screen.onboarding.viewmodel

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class OnBoardingPage(
    val imageRes: DrawableResource,
    val title: StringResource,
    val subtitle: StringResource
)