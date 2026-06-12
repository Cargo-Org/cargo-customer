package com.example.cargo_customer.presentation.core.ui

import org.jetbrains.compose.resources.StringResource

sealed interface UiText {
    data class Dynamic(val value: String) : UiText
    data class Resource(val res: StringResource) : UiText
    data class ResourceWithArgs(
        val resource: StringResource,
        val args: List<Any>
    ) : UiText
    data class Joined(
        val texts: List<UiText>,
        val separator: String = "\n",
        val prefix: String = "",
        val postfix: String = ""
    ) : UiText
}