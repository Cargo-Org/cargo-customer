package com.cargo.customer.shared.domain.result

import com.cargo.customer.shared.domain.model.ValidationError

sealed interface ValidationResult {
    data object Success : ValidationResult
    data class Error(
        val error: ValidationError
    ) : ValidationResult
}