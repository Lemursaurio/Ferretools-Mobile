package com.example.ferretools.model.registro

data class IniciarSesionUiState(
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isFormValid: Boolean = false
)
