package com.cargo.customer.shared.domain.validation

sealed interface ValidationError {
    data object InvalidEmail : ValidationError
    data object InvalidFullName : ValidationError
    data object WeakPassword : ValidationError
    data object PasswordNeedsNumber : ValidationError
    data object PasswordNeedsLetter : ValidationError
    data object InvalidPhone : ValidationError
}