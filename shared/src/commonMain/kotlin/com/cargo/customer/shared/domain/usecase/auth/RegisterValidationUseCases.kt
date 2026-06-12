package com.cargo.customer.shared.domain.usecase.auth

import com.cargo.customer.shared.domain.usecase.validation.ValidateEmailUseCase
import com.cargo.customer.shared.domain.usecase.validation.ValidateNameUseCase
import com.cargo.customer.shared.domain.usecase.validation.ValidatePasswordUseCase
import com.cargo.customer.shared.domain.usecase.validation.ValidatePhoneUseCase

data class RegisterValidationUseCases(
    val validateName: ValidateNameUseCase,
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase,
    val validatePhone: ValidatePhoneUseCase
)