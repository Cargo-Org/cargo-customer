package com.example.cargo_customer.presentation.theme.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cargo_customer.presentation.theme.AppTheme


@Composable
fun OnboardingScreen(){
    OnboardingScreenContent()
}

@Composable
private fun OnboardingScreenContent(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize().background(AppTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Onboarding",
            style = AppTheme.typography.headlineLg,
            color = AppTheme.colors.onBackground
        )
    }
}