package com.example.ferretools.model.registro

import android.net.Uri
import com.example.ferretools.model.enums.RolUsuario

data class RegistroUsuarioUiState(
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var showPassword: Boolean = false,
    var showConfirmPassword: Boolean = false,
    val imageUri: Uri? = null,
    val rolUsuario: RolUsuario = RolUsuario.CLIENTE,
    val emailError: String? = "",
    val passwordError: String? = "",
    val confirmPasswordError: String? = "",
    val areFieldsFilled: Boolean = false,
    val isFormValid: Boolean = false
)