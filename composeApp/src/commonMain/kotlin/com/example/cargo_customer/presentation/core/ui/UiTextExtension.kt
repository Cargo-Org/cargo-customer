package com.example.cargo_customer.presentation.core.ui

import androidx.compose.runtime.Composable
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.invalid_email
import cargo_customer.composeapp.generated.resources.invalid_full_name
import cargo_customer.composeapp.generated.resources.invalid_phone
import cargo_customer.composeapp.generated.resources.password_needs_letter
import cargo_customer.composeapp.generated.resources.password_needs_number
import cargo_customer.composeapp.generated.resources.weak_password
import com.cargo.customer.shared.domain.model.ValidationError
import com.cargo.customer.shared.domain.result.ValidationResult
import org.jetbrains.compose.resources.stringResource

@Composable
fun UiText.asString(): String {
    return when (this) {
        is UiText.Dynamic -> value
        is UiText.Resource -> stringResource(res)
        is UiText.ResourceWithArgs -> {
            val resolvedArgs = args.map { arg ->
                if (arg is UiText) {
                    arg.asString()
                } else {
                    arg
                }
            }.toTypedArray()
            stringResource(resource, *resolvedArgs)
        }
        is UiText.Joined -> {
            val result = StringBuilder(prefix)
            for (i in texts.indices) {
                if (i > 0) {
                    result.append(separator)
                }
                result.append(texts[i].asString())
            }
            result.append(postfix)
            result.toString()
        }
    }
}

fun ValidationError.asUiText(): UiText {
    return when (this) {

        ValidationError.InvalidEmail ->
            UiText.Resource(Res.string.invalid_email)

        ValidationError.InvalidFullName ->
            UiText.Resource(Res.string.invalid_full_name)

        ValidationError.WeakPassword ->
            UiText.Resource(Res.string.weak_password)

        ValidationError.PasswordNeedsLetter ->
            UiText.Resource(Res.string.password_needs_letter)

        ValidationError.PasswordNeedsNumber ->
            UiText.Resource(Res.string.password_needs_number)

        ValidationError.InvalidPhone ->
            UiText.Resource(Res.string.invalid_phone)
    }
}


fun ValidationResult.toUiTextOrNull(): UiText? {
    return (this as? ValidationResult.Error)
        ?.error
        ?.asUiText()
}
