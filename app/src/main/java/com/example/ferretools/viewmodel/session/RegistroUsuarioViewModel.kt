package com.example.ferretools.viewmodel.session
import android.net.Uri
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.getValue // Para acceso directo al valor
import androidx.compose.runtime.setValue // Para modificar el valor
import com.example.ferretools.model.database.Usuario
import com.example.ferretools.model.enums.RolUsuario
import com.example.ferretools.model.registro.RegistroUiState
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class RegistroUsuarioViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(RegistroUiState())
    val uiState = _uiState.asStateFlow()

    private val db = Firebase.firestore

    fun setRol(rolUsuario: RolUsuario) {
        _uiState.update {
            it.copy(rolUsuario = rolUsuario)
        }
    }

    private fun updateState(transform: (RegistroUiState) -> RegistroUiState) {
        _uiState.update { current ->
            val updated = transform(current)
            updated.copy(isFormValid = isFormValid(updated))
        }
    }

    fun updateName(name: String) {
        updateState { it.copy(name = name) }
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

    fun updatePhone(phone: String) {
        updateState { it.copy(phone = phone) }
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

    fun updateConfirmPassword(confirmPassword: String) {
        updateState {
            it.copy(
                confirmPassword = confirmPassword,
                confirmPasswordError = if (_uiState.value.password != confirmPassword)
                    "Las contraseñas no coinciden" else null
            )
        }
    }

    fun updateUri(uri: Uri?) {
        _uiState.update {
            it.copy(imageUri = uri)
        }
    }

    fun toggleShowPassword() {
        _uiState.update { it.copy(showPassword = !_uiState.value.showPassword) }
    }

    fun toggleShowConfirmPassword() {
        _uiState.update { it.copy(showConfirmPassword = !_uiState.value.showConfirmPassword) }
    }

    fun areFieldsFilled(state: RegistroUiState): Boolean {
        return listOf(
            state.name,
            state.email,
            state.phone,
            state.password,
            state.confirmPassword
        ).all { it.isNotBlank() }
    }

    private fun isFormValid(state: RegistroUiState): Boolean {
        return state.emailError == null &&
                state.passwordError == null &&
                state.confirmPasswordError == null &&
                areFieldsFilled(state)
    }

    fun createNewUser(): Usuario {
        return Usuario(
            nombre = _uiState.value.name,
            correo = _uiState.value.email,
            celular = _uiState.value.phone,
            contrasena = _uiState.value.password,
            foto = _uiState.value.imageUri,
            rol = _uiState.value.rolUsuario,
            negocio = ""
        )
    }

    fun registerUser(newUser: Usuario) {

        val docRef = db.collection("usuarios").document()

        docRef.set(newUser)
            .addOnSuccessListener {
                Log.d("FIREBASE", "Documento creado correctamente")
            }
            .addOnFailureListener {
                Log.e("FIREBASE", "Error: ${it.message}")
            }
    }

}