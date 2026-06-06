package com.example.cargo_customer

import androidx.compose.runtime.Composable
import com.example.cargo_customer.presentation.navigation.NavGraph
import com.example.cargo_customer.presentation.theme.CargoCustomerTheme

@Composable
fun App() {
    CargoCustomerTheme {
        NavGraph()
    }
}