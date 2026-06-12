package com.cargo.customer.shared.domain.usecase.validation

import com.cargo.customer.shared.domain.model.ValidationError
import com.cargo.customer.shared.domain.result.ValidationResult


class ValidateEmailUseCase {
    operator fun invoke(email: String): ValidationResult {
        return if (email.trim().matches(EMAIL_REGEX)) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(
                ValidationError.InvalidEmail
            )
        }
    }
    companion object {
        private val EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    }
}