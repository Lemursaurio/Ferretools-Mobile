package com.example.ferretools.viewmodel.session

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ferretools.model.database.Negocio
import com.example.ferretools.model.database.Usuario
import com.example.ferretools.model.registro.RegistroNegocioUiState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistroNegocioViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(RegistroNegocioUiState())
    val uiState = _uiState.asStateFlow()

    private val db = Firebase.firestore

    fun setOwnerId(ownerId: String) {
        _uiState.update {
            it.copy(ownerId = ownerId)
        }
    }

    // Función para comprobar la validez del forms después de cambiar cualquier valor
    private fun updateState(transform: (RegistroNegocioUiState) -> RegistroNegocioUiState) {
        _uiState.update { current ->
            val updated = transform(current)
            updated.copy(isFormValid = isFormValid(updated))
        }
    }

    fun updateBusinessName(businessName: String) {
        updateState {
            it.copy(businessName = businessName)
        }
    }

    fun updateBusinessType(businessType: String) {
        updateState {
            it.copy(businessType = businessType)
        }
    }

    fun updateAddress(address: String) {
        updateState {
            it.copy(address = address)
        }
    }

    fun updateRuc(ruc: String) {
        updateState {
            it.copy(ruc = ruc)
        }
    }

    fun updateLogoUri(uri: Uri?) {
        updateState {
            it.copy(logoUri = uri)
        }
    }

    fun registerBusiness() {
        val newBusiness = Negocio(
            nombre = _uiState.value.businessName,
            tipo = _uiState.value.businessType,
            direccion = _uiState.value.address,
            ruc = _uiState.value.ruc,
            gerenteId = _uiState.value.ownerId,
            logoUri = _uiState.value.logoUri
        )

        val docRef = db.collection("negocios").document()

        docRef.set(newBusiness)
            .addOnSuccessListener {
                Log.d("FIREBASE", "Documento creado correctamente")
            }
            .addOnFailureListener {
                Log.e("FIREBASE", "Error: ${it.message}")
            }
    }

    fun isFormValid(state: RegistroNegocioUiState): Boolean {
        return state.businessName != "" &&
                state.businessType != "" &&
                state.address != "" &&
                state.ruc != ""
    }
}