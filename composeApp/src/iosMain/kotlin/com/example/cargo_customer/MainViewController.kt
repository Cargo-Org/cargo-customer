package com.example.cargo_customer

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) {
    App()
}