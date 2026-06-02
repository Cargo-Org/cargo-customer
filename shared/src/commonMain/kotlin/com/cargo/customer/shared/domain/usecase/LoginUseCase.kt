package com.cargo.customer.shared.domain.usecase

import com.cargo.customer.shared.data.remote.dto.LoginRequestDto
import com.cargo.customer.shared.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.loginWithEmailAndPassword(LoginRequestDto(email, password))


    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return email.isNotBlank() && emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }
}
// email , phone , password , full name
