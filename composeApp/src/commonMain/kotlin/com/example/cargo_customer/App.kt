package com.example.cargo_customer

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.cargo_customer.presentation.login.LoginScreen
import com.example.cargo_customer.presentation.register.RegisterScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        LoginScreen()
    }
}