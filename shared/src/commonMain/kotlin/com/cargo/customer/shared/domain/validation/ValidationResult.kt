package com.cargo.customer.shared.domain.validation

sealed interface ValidationResult {
    data object Success : ValidationResult
    data class Error(
        val error: ValidationError
    ) : ValidationResult
}