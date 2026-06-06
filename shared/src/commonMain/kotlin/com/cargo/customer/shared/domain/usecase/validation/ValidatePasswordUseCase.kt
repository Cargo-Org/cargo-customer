package com.cargo.customer.shared.domain.usecase.validation

import com.cargo.customer.shared.domain.model.ValidationError
import com.cargo.customer.shared.domain.result.ValidationResult


class ValidatePasswordUseCase {

    operator fun invoke(password: String): ValidationResult {

        return when {
            password.length < 8 -> {
                ValidationResult.Error(
                    ValidationError.WeakPassword
                )
            }

            !password.any { it.isDigit() } -> {
                ValidationResult.Error(
                    ValidationError.PasswordNeedsNumber
                )
            }

            !password.any { it.isLetter() } -> {
                ValidationResult.Error(
                    ValidationError.PasswordNeedsLetter
                )
            }

            else -> ValidationResult.Success
        }
    }
}