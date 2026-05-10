package com.example.cargo_customer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.compose_multiplatform
import com.example.cargo_customer.presentation.auth.login.LoginScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        LoginScreen()
    }
}