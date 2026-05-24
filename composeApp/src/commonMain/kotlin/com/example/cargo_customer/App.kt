package com.example.cargo_customer

import androidx.compose.runtime.*
import com.example.cargo_customer.presentation.navigation.NavGraph
import com.example.cargo_customer.presentation.navigation.Route
import com.example.cargo_customer.presentation.theme.CargoCustomerTheme

@Composable
fun App() {
    CargoCustomerTheme {
        NavGraph(
            startDestination = Route.SplashRoute
        )
    }
}