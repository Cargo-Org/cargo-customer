package com.example.cargo_customer

import androidx.compose.runtime.*
import com.example.cargo_customer.presentation.theme.CargoCustomerTheme
import com.example.cargo_customer.presentation.verify_email.EmailVerificationScreen

@Composable
fun App() {
    CargoCustomerTheme {
        NavGraph(
            startDestination = Route.LoginRoute //this will change according to start screen
        )
    }
}