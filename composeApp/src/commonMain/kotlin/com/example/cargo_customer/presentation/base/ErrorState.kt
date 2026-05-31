package com.example.cargo_customer.presentation.base

sealed class ErrorState{
    data class UnknownError(val message: String?) : ErrorState()
}