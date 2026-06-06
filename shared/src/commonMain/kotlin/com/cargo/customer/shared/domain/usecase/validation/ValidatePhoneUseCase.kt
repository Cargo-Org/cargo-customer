package com.cargo.customer.shared.domain.usecase.validation

import com.cargo.customer.shared.domain.model.ValidationError
import com.cargo.customer.shared.domain.result.ValidationResult

class ValidatePhoneUseCase {

    private val regex =
        Regex("^\\+[1-9][0-9]{7,14}$")

    operator fun invoke(phone: String): ValidationResult {
        return if (phone.matches(regex)) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(
                ValidationError.InvalidPhone
            )
        }
    }
}