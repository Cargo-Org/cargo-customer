package com.example.cargo_customer.presentation.mapper

import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.email_required
import cargo_customer.composeapp.generated.resources.first_name_required
import cargo_customer.composeapp.generated.resources.invalid_email
import cargo_customer.composeapp.generated.resources.invalid_phone_format
import cargo_customer.composeapp.generated.resources.last_name_required
import cargo_customer.composeapp.generated.resources.password_min_length
import cargo_customer.composeapp.generated.resources.password_required
import cargo_customer.composeapp.generated.resources.phone_required
import cargo_customer.composeapp.generated.resources.unexpected_error
import com.example.cargo_customer.presentation.utils.ApiErrorMessage
import com.example.cargo_customer.presentation.core.ui.UiText
import org.jetbrains.compose.resources.StringResource

fun ApiErrorMessage.toRes(): StringResource {
    return when (this) {
        ApiErrorMessage.EMAIL_REQUIRED -> Res.string.email_required
        ApiErrorMessage.INVALID_EMAIL -> Res.string.invalid_email
        ApiErrorMessage.PASSWORD_REQUIRED -> Res.string.password_required
        ApiErrorMessage.PASSWORD_TOO_SHORT -> Res.string.password_min_length
        ApiErrorMessage.FIRST_NAME_REQUIRED -> Res.string.first_name_required
        ApiErrorMessage.LAST_NAME_REQUIRED -> Res.string.last_name_required
        ApiErrorMessage.PHONE_REQUIRED -> Res.string.phone_required
        ApiErrorMessage.INVALID_PHONE_FORMAT -> Res.string.invalid_phone_format
        ApiErrorMessage.UNKNOWN -> Res.string.unexpected_error
    }
}

fun mapApiErrors(
    errors: List<String>
): List<UiText> {
    return errors.map { message ->
        val errorEnum = ApiErrorMessage.from(message)
        UiText.Resource(errorEnum.toRes())
    }
}