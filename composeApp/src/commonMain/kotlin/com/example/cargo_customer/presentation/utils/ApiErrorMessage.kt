package com.example.cargo_customer.presentation.utils

enum class ApiErrorMessage(val message: String) {

    EMAIL_REQUIRED("Email is required."),
    INVALID_EMAIL("Email must be a valid email address."),

    PASSWORD_REQUIRED("Password is required."),
    PASSWORD_TOO_SHORT("Password must be at least 8 characters."),

    FIRST_NAME_REQUIRED("First name is required."),
    LAST_NAME_REQUIRED("Last name is required."),

    PHONE_REQUIRED("Phone number is required."),
    INVALID_PHONE_FORMAT("Phone number must be in E.164 format (e.g. +201012345678)."),
    UNKNOWN("Unknown error.");
    companion object {
        fun from(message: String): ApiErrorMessage {
            return entries.firstOrNull { it.message == message }
                ?: UNKNOWN
        }
    }

}