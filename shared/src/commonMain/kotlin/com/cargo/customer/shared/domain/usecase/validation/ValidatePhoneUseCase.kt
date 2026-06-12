package com.cargo.customer.shared.domain.usecase.validation

import com.cargo.customer.shared.domain.model.ValidationError
import com.cargo.customer.shared.domain.result.ValidationResult

class ValidatePhoneUseCase {
    operator fun invoke(phone: String): ValidationResult {
        return if (phone.trim().matches(PHONE_REGEX)) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(
                ValidationError.InvalidPhone
            )
        }
    }
    companion object {
        private val PHONE_REGEX =
            "^\\+[1-9][0-9]{7,14}$".toRegex()
    }
}