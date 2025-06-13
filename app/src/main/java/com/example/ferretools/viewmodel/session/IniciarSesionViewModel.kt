package com.example.ferretools.viewmodel.session

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.ferretools.model.registro.IniciarSesionUiState
import com.example.ferretools.model.registro.RegistroNegocioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IniciarSesionViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(IniciarSesionUiState())
    val uiState = _uiState.asStateFlow()

    // Función para comprobar la validez del forms después de cambiar cualquier valor
    private fun updateState(transform: (IniciarSesionUiState) -> IniciarSesionUiState) {
        _uiState.update { current ->
            val updated = transform(current)
            updated.copy(isFormValid = isFormValid(updated))
        }
    }

    fun updateEmail(email: String) {
        updateState {
            it.copy(
                email = email,
                emailError = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    "Correo inválido" else null
            )
        }
    }

    fun updatePassword(password: String) {
        updateState {
            it.copy(
                password = password,
                passwordError = if (password.length < 6)
                    "Mínimo 6 caracteres" else null
            )
        }
    }

    fun toggleShowPassword() {
        _uiState.update {
            it.copy(showPassword = !it.showPassword)
        }
    }

    fun isFormValid(state: IniciarSesionUiState): Boolean {
        return state.emailError == null && state.passwordError == null
    }
}