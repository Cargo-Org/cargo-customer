package com.example.cargo_customer.presentation.core.ui.ui

import org.jetbrains.compose.resources.StringResource

sealed interface UiText {
    data class Dynamic(val value: String) : UiText
    data class Resource(val res: StringResource) : UiText
}