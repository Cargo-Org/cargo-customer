package com.example.cargo_customer

import androidx.compose.runtime.*
import com.example.cargo_customer.presentation.theme.CargoCustomerTheme
import com.example.cargo_customer.presentation.navigation.NavGraph
import com.example.cargo_customer.presentation.navigation.Route

@Composable
fun App() {
    CargoCustomerTheme {
        EmailVerificationScreen()
    }
}