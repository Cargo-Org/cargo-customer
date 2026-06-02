package com.cargo.customer.shared.domain.validation

object Validator {

    fun validateName(name: String): ValidationResult {
        val parts = name.trim().split(" ")

        if (parts.size < 2 ||
            parts.first().isBlank() ||
            parts.drop(1).joinToString(" ").isBlank()
        ) {
            return ValidationResult.Error(
                ValidationError.InvalidFullName
            )
        }

        return ValidationResult.Success
    }

    fun validateEmail(email: String): ValidationResult {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

        return if (email.matches(regex)) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(
                ValidationError.InvalidEmail
            )
        }
    }

    fun validatePassword(password: String): ValidationResult {
        return when {
            password.length < 8 ->
                ValidationResult.Error(
                    ValidationError.WeakPassword
                )

            !password.any { it.isDigit() } ->
                ValidationResult.Error(
                    ValidationError.PasswordNeedsNumber
                )

            !password.any { it.isLetter() } ->
                ValidationResult.Error(
                    ValidationError.PasswordNeedsLetter
                )

            else -> ValidationResult.Success
        }
    }

    fun validatePhone(phone: String): ValidationResult {
        val regex = Regex("^\\+[1-9][0-9]{7,14}$")

        return if (phone.matches(regex)) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(
                ValidationError.InvalidPhone
            )
        }
    }
}