package com.cargo.customer.shared.domain.usecase.auth

import com.cargo.customer.shared.domain.usecase.validation.ValidateEmailUseCase
import com.cargo.customer.shared.domain.usecase.validation.ValidatePasswordUseCase

data class LoginValidationUseCases(
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase,
)