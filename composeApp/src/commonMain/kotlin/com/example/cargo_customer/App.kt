package com.example.cargo_customer

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.cargo_customer.presentation.navigation.NavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavGraph()
    }
}