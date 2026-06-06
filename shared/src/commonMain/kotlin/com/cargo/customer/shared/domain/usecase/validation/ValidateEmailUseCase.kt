package com.cargo.customer.shared.domain.usecase.validation

import com.cargo.customer.shared.domain.model.ValidationError
import com.cargo.customer.shared.domain.result.ValidationResult


class ValidateEmailUseCase {

    private val regex =
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    operator fun invoke(email: String): ValidationResult {
        return if (email.matches(regex)) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(
                ValidationError.InvalidEmail
            )
        }
    }
}