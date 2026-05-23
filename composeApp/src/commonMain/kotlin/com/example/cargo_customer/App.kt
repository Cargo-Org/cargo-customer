package com.example.cargo_customer

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.cargo_customer.presentation.navigation.NavGraph
import com.example.cargo_customer.presentation.navigation.Route
import com.example.cargo_customer.presentation.theme.CargoCustomerTheme

@Composable
@Preview
fun App() {
    CargoCustomerTheme {
        NavGraph(
            startDestination = Route.LoginRoute //this will change according to start screen
        )
    }
}