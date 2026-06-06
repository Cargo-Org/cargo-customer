package com.cargo.customer.shared.domain.usecase.validation

import com.cargo.customer.shared.domain.model.ValidationError
import com.cargo.customer.shared.domain.result.ValidationResult

class ValidateNameUseCase {

    operator fun invoke(name: String): ValidationResult {
        val parts = name.trim().split(" ")

        return if (
            parts.size < 2 ||
            parts.first().isBlank() ||
            parts.drop(1).joinToString(" ").isBlank()
        ) {
            ValidationResult.Error(
                ValidationError.InvalidFullName
            )
        } else {
            ValidationResult.Success
        }
    }
}